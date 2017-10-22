package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.domain.DeptType;
import com.chilicool.hdtools.domain.DeptTypeExample;
import com.chilicool.hdtools.domain.ProjBaseInfo;
import com.chilicool.hdtools.domain.VersionInfo;
import com.chilicool.hdtools.model.ProjWithAction;
import com.chilicool.hdtools.service.ProjBaseInfoService;
import com.chilicool.hdtools.service.ProjDeptInfoService;
import com.chilicool.hdtools.service.ProjectService;
import com.chilicool.hdtools.service.core.version.VersionService;
import com.chilicool.hdtools.support.DateUtil;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chilicool on 2017/10/7.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private VersionService versionService;
    @Autowired
    private ProjBaseInfoService projBaseInfoService;
    @Autowired
    private ProjDeptInfoService projDeptInfoService;

    @Override
    public void initProjForAdd(String projPhase, Map<String, Object> returnMap) {
        ProjBaseInfo projBaseInfo = projBaseInfoService.initProjForAdd(projPhase);
        loadProjInfoIntoMap(projBaseInfo, returnMap);
    }

    @Override
    public void loadExisProjInfo(Long projId, Map<String, Object> returnMap) {
        ProjBaseInfo projBaseInfo = projBaseInfoService.loadProjBaseInfoById(projId);
        loadProjInfoIntoMap(projBaseInfo, returnMap);
    }

    // 项目信息存入Map
    private void loadProjInfoIntoMap(ProjBaseInfo projBaseInfo, Map<String, Object> returnMap){
        Map projBaseInfoMap = new BeanMap(projBaseInfo);
        returnMap.putAll(projBaseInfoMap);
        reformatCreateTimeInMap(returnMap);
    }

    @Override
    public Map<String, Object> initProject(String action, Long projId) {
        Map<String, Object> returnMap = new HashMap<>();

        if (BusiConst.Action.ADD.equals(action)) {
            // 初始化版本信息
            initVersion(returnMap);
            // 初始化项目识别标识
            initProjGroup(returnMap);
            // 初始化项目基本信息
            initProjBaseInfo(returnMap);
        } else {
            // 加载已存在项目信息
            loadExisProjInfo(projId, returnMap);
        }

        // 初始化部门相关信息
        initDeptInfo(returnMap);

        return returnMap;
    }

    @Override
    public ProjBaseInfo initAndUpdateProject(ProjWithAction projWithAction) {
        String action = projWithAction.getAction();

        // 保存项目基本信息
        ProjBaseInfo projBaseInfo = new ProjBaseInfo();
        BeanUtils.copyProperties(projWithAction, projBaseInfo);

        Map<String, Object> returnMap = new HashMap<>();
        if (BusiConst.Action.ADD.equals(action)) {
            // 初始化版本信息
            initVersion(returnMap);
            // 初始化项目识别标识
            initProjGroup(returnMap);
            // 初始化项目基本信息
            initProjBaseInfo(returnMap, projBaseInfo);
            // 初始化部门分类信息
            initDeptTypeInfo(returnMap);
            // 初始化部门汇总信息
            initDeptSumyInfo(returnMap);
        } else {
            projBaseInfoService.saveProjBaseInfo(projBaseInfo);
            returnMap.put("projId", projBaseInfo.getId());
        }

        Long projId = (Long) returnMap.get("projId");
        return projBaseInfoService.loadProjBaseInfoById(projId);
    }


    private void reformatCreateTimeInMap(Map<String, Object> map) {
        // 创建时间重新格式化
        Object createTime = map.get("createTime");
        if (null != createTime && createTime instanceof Date) {
            String createTimeStr = DateUtil.format((Date) createTime, DateUtil.DFPattern.YYY_MM_DD_HH_MM_SS);
            map.put("createTime", createTimeStr);
        }
    }

    private void initDeptInfo(Map<String, Object> returnMap) {
        Long projId = (Long) returnMap.get("id");

        if (null == projId || !ifDeptInfoExist(projId)) {
            // 初始化部门分类信息
            initDeptTypeInfo(returnMap);
            // 初始化部门汇总信息
            initDeptSumyInfo(returnMap);
        }
    }

    @Override
    public List<ProjBaseInfo> loadAllProjInfo() {
        return projBaseInfoService.loadAllProjInfo();
    }

    /**
     * 创建项目版本信息
     *
     * @param returnMap
     */
    private void initVersion(Map<String, Object> returnMap) {
        VersionInfo versionInfo = versionService.createVersion();
        returnMap.put("verId", versionInfo.getId());
        returnMap.put("verInfo", versionInfo.getVerInfo());
    }

    /**
     * 创建项目识别标记
     *
     * @param returnMap
     */
    private void initProjGroup(Map<String, Object> returnMap) {
        Long projGroupId = projBaseInfoService.initProjGroup();
        returnMap.put("groupId", projGroupId);
    }

    /**
     * 创建项目基础信息
     *
     * @param returnMap
     */
    private void initProjBaseInfo(Map<String, Object> returnMap, ProjBaseInfo projBaseInfo) {
        projBaseInfo.setGroupId((Long) returnMap.get("groupId"));
        projBaseInfo.setVerId((Long) returnMap.get("verId"));
        projBaseInfo.setVerInfo(returnMap.get("verInfo").toString());

        Long projId = projBaseInfoService.initProjBaseInfoWithParams(projBaseInfo);
        returnMap.put("projId", projId);
    }

    /**
     * 创建项目基础信息
     *
     * @param returnMap
     */
    @Deprecated
    private void initProjBaseInfo(Map<String, Object> returnMap) {
        Long projId = projBaseInfoService.initProjBaseInfo((Long) returnMap.get("groupId"),
                (Long) returnMap.get("verId"), returnMap.get("verInfo").toString());
        returnMap.put("projId", projId);
    }

    /**
     * 创建部门分类信息
     *
     * @param returnMap
     */
    private void initDeptTypeInfo(Map<String, Object> returnMap) {
        projDeptInfoService.initProjDeptType((Long) returnMap.get("projId"));
    }

    /**
     * 创建部门分类汇总信息
     *
     * @param returnMap
     */
    private void initDeptSumyInfo(Map<String, Object> returnMap) {
        projDeptInfoService.initProjDeptSumy((Long) returnMap.get("projId"));
    }

    /**
     * 是否已创建部门分类信息
     * @param projId
     * @return
     */
    private boolean ifDeptInfoExist(Long projId){
        return projDeptInfoService.ifDeptInfoExist(projId);
    }
}
