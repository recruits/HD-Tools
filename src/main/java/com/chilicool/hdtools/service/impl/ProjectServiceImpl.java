package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.domain.ProjGroup;
import com.chilicool.hdtools.domain.VersionInfo;
import com.chilicool.hdtools.service.ProjBaseInfoService;
import com.chilicool.hdtools.service.ProjDeptInfoService;
import com.chilicool.hdtools.service.ProjectService;
import com.chilicool.hdtools.service.core.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Map<String, Object> initProject(String action) {
        Map<String, Object> returnMap = null;

        if (BusiConst.Action.ADD.equals(action)) {
            returnMap = new HashMap<>();

            // 初始化版本信息
            initVersion(returnMap);
            // 初始化项目识别标识
            initProjGroup(returnMap);
            // 初始化项目基本信息
            initProjBaseInfo(returnMap);
            // 初始化部门分类信息
            initDeptTypeInfo(returnMap);
            // 初始化部门汇总信息
            initDeptSumyInfo(returnMap);
        }

        return returnMap;
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
    private void initProjBaseInfo(Map<String, Object> returnMap) {
        Long projId = projBaseInfoService.initProjBaseInfo((Long) returnMap.get("groupId"), (Long) returnMap.get("verId"));
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
}
