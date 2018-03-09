package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.ProjWithAction;
import com.chilicool.hdtools.service.*;
import com.chilicool.hdtools.service.core.version.VersionService;
import com.chilicool.hdtools.support.DateUtil;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private ProjAreaInfoService projAreaInfoService;
    @Autowired
    private ProjRoomInfoService projRoomInfoService;
    @Autowired
    private RoomDataService roomDataService;

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
    @Deprecated
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

    @Override
    public ProjBaseInfo releaseProject(Long projId, Long groupId) {
        // 获取版本信息
        Map<String, Object> verInfo = projBaseInfoService.getProjUpgradeVersion(projId, groupId);

        // 升级版本
        VersionInfo versionInfo = versionService.createVersion((Long) verInfo.get(ProjKeys.VER_ID), verInfo.get("verCode").toString());

        // 拷贝整个项目
        Long newProjId = startToCopyProjectInfo(projId, groupId, versionInfo.getId(), versionInfo.getVerInfo(), OperType.RELEASE);

        return projBaseInfoService.loadProjBaseInfoById(newProjId);
    }

    @Override
    public void cloneProject(Long projId) {
        // 创建版本
        VersionInfo versionInfo = versionService.createVersion();

        // 创建项目分组
        Long newGroupId = projBaseInfoService.initProjGroup();

        // 拷贝整个项目
        Long newProjId = startToCopyProjectInfo(projId, newGroupId, versionInfo.getId(), versionInfo.getVerInfo(), OperType.COPY);

        //return projBaseInfoService.loadProjBaseInfoById(newProjId);
    }

    private Long startToCopyProjectInfo(Long srcProjId, Long groupId, Long verId, String verInfo, OperType operType) {
        Map<String, Object> cacheMap = new HashMap<>();
        // 加载基础参数信息
        loadParamsIntoCache(cacheMap, srcProjId, groupId, verId, verInfo, operType);

        // 拷贝基本信息
        copyProjBaseInfo(cacheMap);

        // 拷贝汇总信息
        copyProjSumyInfo(cacheMap);

        // 拷贝部门分类
        copyProjDeptType(cacheMap);

        return (Long) cacheMap.get(ProjKeys.PROJ_ID);
    }

    // 存储基础信息
    private void loadParamsIntoCache(Map<String, Object> cacheMap, final Long srcProjId, Long groupId,
                                     Long verId, String verInfo, OperType operType) {
        cacheMap.put(ProjKeys.SRC_PROJ_ID, srcProjId);
        cacheMap.put(ProjKeys.GROUP_ID, groupId);
        cacheMap.put(ProjKeys.VER_ID, verId);
        cacheMap.put(ProjKeys.VER_INFO, verInfo);

        cacheMap.put(ProjKeys.CREATE_TIME, new Date());

        cacheMap.put(ProjKeys.OPER_TYPE, operType);
    }

    // 拷贝基础信息
    private void copyProjBaseInfo(Map<String, Object> cacheMap) {
        ProjBaseInfo srcProjBaseInfo = projBaseInfoService.loadProjBaseInfoById((Long) cacheMap.get(ProjKeys.SRC_PROJ_ID));
        ProjBaseInfo dstProjBaseInfo = new ProjBaseInfo();

        // 复制项目基础信息
        BeanUtils.copyProperties(srcProjBaseInfo, dstProjBaseInfo);

        // 更新项目识别标识
        dstProjBaseInfo.setGroupId((Long) cacheMap.get(ProjKeys.GROUP_ID));

        // 更新版本信息
        dstProjBaseInfo.setVerId((Long) cacheMap.get(ProjKeys.VER_ID));
        dstProjBaseInfo.setVerInfo((String) cacheMap.get(ProjKeys.VER_INFO));

        // 清空项目编号
        dstProjBaseInfo.setId(null);

        // 更新创建时间
        dstProjBaseInfo.setCreateTime((Date)cacheMap.get(ProjKeys.CREATE_TIME));

        // 更新项目名称
        rebuildProjBaseName(dstProjBaseInfo, cacheMap);

        // 保存项目信息
        projBaseInfoService.saveProjBaseInfo(dstProjBaseInfo);

        // 更新项目编号
        cacheMap.put(ProjKeys.PROJ_ID, dstProjBaseInfo.getId());
    }

    // 复制项目，修改项目名称
    private void rebuildProjBaseName(ProjBaseInfo dstProjBaseInfo, Map<String, Object> cacheMap) {
        if (OperType.COPY.equals((OperType) cacheMap.get(ProjKeys.OPER_TYPE))) {
            dstProjBaseInfo.setProjName(dstProjBaseInfo.getProjName() + APPEND_PROJ_NAME);
        }
    }

    // 拷贝部门汇总信息
    private void copyProjSumyInfo(Map<String, Object> cacheMap){
        DeptSummary srcDeptSummary = projDeptInfoService.loadDeptSummaryInfo((Long) cacheMap.get(ProjKeys.SRC_PROJ_ID));
        DeptSummary dstDeptSummary = new DeptSummary();

        // 复制部门汇总信息
        BeanUtils.copyProperties(srcDeptSummary, dstDeptSummary);

        // 重置项目编号信息
        dstDeptSummary.setProjId((Long) cacheMap.get(ProjKeys.PROJ_ID));

        // 清空部门汇总编号
        dstDeptSummary.setId(null);

        // 更新创建时间
        dstDeptSummary.setCreateTime((Date)cacheMap.get(ProjKeys.CREATE_TIME));

        // 保存部门汇总信息
        projDeptInfoService.saveDeptSummaryInfo(dstDeptSummary);
    }

    // 拷贝全部部门分类信息
    private void copyProjDeptType(Map<String, Object> cacheMap){
        List<DeptType> deptTypes = projDeptInfoService.loadDeptTypeByProjId((Long) cacheMap.get(ProjKeys.SRC_PROJ_ID));

        if(CollectionUtils.isNotEmpty(deptTypes)){
            Long projId = (Long) cacheMap.get(ProjKeys.PROJ_ID);
            Date createTime = (Date)cacheMap.get(ProjKeys.CREATE_TIME);
            for(DeptType srcDeptType: deptTypes){
                // 存储源部门分类编号
                cacheMap.put(ProjKeys.SRC_DEPT_TYPE_ID, srcDeptType.getId());

                // 复制部门分类信息
                Long deptTypeId = copyProjDeptType(srcDeptType, projId, createTime);
                cacheMap.put(ProjKeys.DEPT_TYPE_ID, deptTypeId);

                // 复制项目部门信息
                copyProjDeptInfo(cacheMap);
            }
        }
    }

    // 拷贝单个部门分类信息
    private Long copyProjDeptType(DeptType srcDeptType, Long projId, Date createTime){
        DeptType dstDeptType = new DeptType();

        // 复制部门分类信息
        BeanUtils.copyProperties(srcDeptType, dstDeptType);

        // 重置项目编号信息
        dstDeptType.setProjId(projId);

        // 清空部门分类编号
        dstDeptType.setId(null);

        // 更新创建时间
        dstDeptType.setCreateTime(createTime);

        // 保存部门分类信息
        projDeptInfoService.saveDeptTypeInfo(dstDeptType);

        return dstDeptType.getId();
    }

    // 拷贝某个部门分类下的全部部门信息
    private void copyProjDeptInfo(Map<String, Object> cacheMap){
        List<Department> departments = projDeptInfoService.loadAllDepartmentByDeptTypeId((Long) cacheMap.get(ProjKeys.SRC_DEPT_TYPE_ID));
        if(CollectionUtils.isNotEmpty(departments)){
            Long projId = (Long) cacheMap.get(ProjKeys.PROJ_ID);
            Long deptTypeId = (Long) cacheMap.get(ProjKeys.DEPT_TYPE_ID);
            Date createTime = (Date)cacheMap.get(ProjKeys.CREATE_TIME);
            for(Department department: departments){
                // 保存源部门编号
                cacheMap.put(ProjKeys.SRC_DEPT_ID, department.getId());

                // 复制部门信息
                Long deptId = copyDepartment(department, projId, deptTypeId, createTime);
                cacheMap.put(ProjKeys.DEPT_ID, deptId);

                // 复制区域汇总信息
                copyAreaSumyInfo(cacheMap);

                // 复制区域信息
                copyAreaInfos(cacheMap);
            }
        }
    }

    // 拷贝单个部门信息
    private Long copyDepartment(Department srcDepartment, Long projId, Long deptTypeId,Date createTime) {
        Department dstDepartment = new Department();

        // 复制部门信息
        BeanUtils.copyProperties(srcDepartment, dstDepartment);

        // 重置项目编号、部门分类编号
        dstDepartment.setProjId(projId);
        dstDepartment.setDeptTypeId(deptTypeId);

        // 清空部门编号
        dstDepartment.setId(null);

        // 更新创建时间
        dstDepartment.setCreateTime(createTime);

        // 保存部门信息
        projDeptInfoService.saveDepartment(dstDepartment);

        return dstDepartment.getId();
    }

    // 拷贝区域汇总信息
    private void copyAreaSumyInfo(Map<String, Object> cacheMap){
        AreaSummary srcAreaSummary = projAreaInfoService.loadAreaSummaryByDeptId((Long) cacheMap.get(ProjKeys.SRC_DEPT_ID));

        if(null != srcAreaSummary){
            AreaSummary dstAreaSummary = new AreaSummary();

            // 拷贝区域汇总信息
            BeanUtils.copyProperties(srcAreaSummary, dstAreaSummary);

            // 重新设置项目编号
            dstAreaSummary.setProjId((Long) cacheMap.get(ProjKeys.PROJ_ID));
            dstAreaSummary.setDeptTypeId((Long) cacheMap.get(ProjKeys.DEPT_TYPE_ID));
            dstAreaSummary.setDeptId((Long) cacheMap.get(ProjKeys.DEPT_ID));

            // 清空汇总信息编号
            dstAreaSummary.setId(null);

            // 更新创建时间
            dstAreaSummary.setCreateTime((Date)cacheMap.get(ProjKeys.CREATE_TIME));

            // 保存区域汇总信息
            projAreaInfoService.saveAreaSummary(dstAreaSummary);
        }
    }

    // 拷贝部门下的区域信息
    private void copyAreaInfos(Map<String, Object> cacheMap) {
        List<AreaInfo> areaInfos = projAreaInfoService.loadAllAreaInfoByDeptId((Long) cacheMap.get(ProjKeys.SRC_DEPT_ID));

        if (CollectionUtils.isNotEmpty(areaInfos)) {
            Long projId = (Long) cacheMap.get(ProjKeys.PROJ_ID);
            Long deptTypeId = (Long) cacheMap.get(ProjKeys.DEPT_TYPE_ID);
            Long deptId = (Long) cacheMap.get(ProjKeys.DEPT_ID);
            Date createTime = (Date)cacheMap.get(ProjKeys.CREATE_TIME);
            for (AreaInfo areaInfo : areaInfos) {
                // 保存源区域编号
                cacheMap.put(ProjKeys.SRC_AREA_ID, areaInfo.getId());

                // 复制区域信息
                Long areaId = copyAreaInfo(areaInfo, projId, deptTypeId, deptId, createTime);
                cacheMap.put(ProjKeys.AREA_ID, areaId);

                // 复制房间信息
                copyRoomInfos(cacheMap);
            }
        }
    }

    // 拷贝单个区域信息
    private Long copyAreaInfo(AreaInfo srcAreaInfo, Long projId, Long deptTypeId, Long deptId, Date createTime){
        AreaInfo dstAreaInfo = new AreaInfo();

        // 拷贝区域信息
        BeanUtils.copyProperties(srcAreaInfo, dstAreaInfo);

        // 设置项目编号、部门分类编号、部门编号
        dstAreaInfo.setProjId(projId);
        dstAreaInfo.setDeptTypeId(deptTypeId);
        dstAreaInfo.setDeptId(deptId);

        // 清空区域编号
        dstAreaInfo.setId(null);

        // 更新创建时间
        dstAreaInfo.setCreateTime(createTime);

        // 保存区域信息
        projAreaInfoService.saveAreaInfo(dstAreaInfo);

        return dstAreaInfo.getId();
    }

    // 拷贝区域下的房间信息
    private void copyRoomInfos(Map<String, Object> cacheMap){
        List<RoomInfo> roomInfos = projRoomInfoService.loadAllRoomInfoByAreaId((Long) cacheMap.get(ProjKeys.SRC_AREA_ID));

        if(CollectionUtils.isNotEmpty(roomInfos)){
            Long projId = (Long) cacheMap.get(ProjKeys.PROJ_ID);
            Long deptTypeId = (Long) cacheMap.get(ProjKeys.DEPT_TYPE_ID);
            Long deptId = (Long) cacheMap.get(ProjKeys.DEPT_ID);
            Long areaId = (Long) cacheMap.get(ProjKeys.AREA_ID);
            Date createTime = (Date)cacheMap.get(ProjKeys.CREATE_TIME);
            for(RoomInfo roomInfo: roomInfos){
                // 设置源房间编号
                cacheMap.put(ProjKeys.SRC_ROOM_ID, roomInfo.getId());

                // 拷贝房间信息
                Long roomId = copyRoomInfo(roomInfo, projId, deptTypeId, deptId, areaId, createTime);
                cacheMap.put(ProjKeys.ROOM_ID, roomId);

                // 拷贝房间参数
                copyRoomParams(cacheMap);
            }
        }
    }

    // 拷贝单个房间信息
    private Long copyRoomInfo(RoomInfo srcRoomInfo, Long projId, Long deptTypeId, Long deptId, Long areaId, Date createTime){
        RoomInfo dstRoomInfo = new RoomInfo();

        // 拷贝房间信息
        BeanUtils.copyProperties(srcRoomInfo, dstRoomInfo);

        // 设置项目编号、部门分类编号、部门编号、区域编号
        dstRoomInfo.setProjId(projId);
        dstRoomInfo.setDeptTypeId(deptTypeId);
        dstRoomInfo.setDeptId(deptId);
        dstRoomInfo.setAreaId(areaId);

        // 清空房间编号
        dstRoomInfo.setId(null);

        // 更新创建时间
        dstRoomInfo.setCreateTime(createTime);

        // 保存房间信息
        projRoomInfoService.saveRoomInfo(dstRoomInfo);

        return dstRoomInfo.getId();
    }

    // 保存房间参数信息
    private void copyRoomParams(Map<String, Object> cacheMap) {
        List<RoomDataDetail> roomDataDetails = roomDataService.loadAllRoomDataDetailByRoomId((Long) cacheMap.get(ProjKeys.SRC_ROOM_ID));

        if (CollectionUtils.isNotEmpty(roomDataDetails)) {
            Long roomId = (Long) cacheMap.get(ProjKeys.ROOM_ID);
            Date createTime = (Date) cacheMap.get(ProjKeys.CREATE_TIME);
            for (RoomDataDetail roomDataDetail : roomDataDetails) {
                // 设置源参数编号
                cacheMap.put(ProjKeys.SRC_PARAM_ID, roomDataDetail.getId());

                // 复制房间参数
                copyRoomParam(roomDataDetail, roomId, createTime);

                // 暂时没有技术参数
            }
        }
    }

    // 拷贝单个房间参数
    private Long copyRoomParam(RoomDataDetail srcRoomDataDetail, Long roomId, Date createTime){
        RoomDataDetail dstRoomDataDetail = new RoomDataDetail();

        // 复制房间参数
        BeanUtils.copyProperties(srcRoomDataDetail, dstRoomDataDetail);

        // 设置房间编号
        dstRoomDataDetail.setRoomId(roomId);

        // 清空参数编号
        dstRoomDataDetail.setId(null);

        // 设置创建时间
        dstRoomDataDetail.setCreateTime(createTime);

        // 保存房间参数
        roomDataService.saveRoomDataDetail(dstRoomDataDetail);

        return dstRoomDataDetail.getId();
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
     * 升级到最新版本
     *
     * @param pid
     * @param nextMajorCode
     * @param returnMap
     */
    private void upgradeVersion(Long pid, String nextMajorCode, Map<String, Object> returnMap) {
        VersionInfo versionInfo = versionService.createVersion(pid, nextMajorCode);
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
