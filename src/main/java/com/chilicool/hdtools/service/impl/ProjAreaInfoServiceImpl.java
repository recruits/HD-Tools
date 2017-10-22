package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.dao.AreaInfoMapper;
import com.chilicool.hdtools.dao.RoomInfoMapper;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.*;
import com.chilicool.hdtools.service.ProjAreaInfoService;
import com.chilicool.hdtools.service.ProjDeptInfoService;
import com.chilicool.hdtools.service.core.deptinfo.DepartmentService;
import com.chilicool.hdtools.service.core.deptinfo.DeptDelService;
import com.chilicool.hdtools.service.core.deptinfo.DeptSumyService;
import com.chilicool.hdtools.service.core.deptinfo.DeptTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chilicool on 2017/10/15.
 */
@Service
public class ProjAreaInfoServiceImpl implements ProjAreaInfoService {
    @Autowired
    private AreaInfoMapper areaInfoMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private DeptTypeService deptTypeService;
    @Autowired
    private DeptSumyService deptSumyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ProjDeptInfoService projDeptInfoService;
    @Autowired
    private DeptDelService deptDelService;

    @Override
    public List<AreaInfoModel> loadAllAreaInfo(Long deptId) {
        List<AreaInfoModel> areaInfoModels = new ArrayList<>();

        Map<String, Object> inParam = new HashMap<>();
        inParam.put("deptId", deptId);
        inParam.put("level", 1);
        // 先查询父级数据
        List<AreaInfoModel> topLevelModels = areaInfoMapper.loadAllAreaInfoWithLevel(inParam);

        inParam.put("level", 2);
        // 再查询子级数据
        List<AreaInfoModel> subLevelModels = areaInfoMapper.loadAllAreaInfoWithLevel(inParam);
        Map<Long, List<Integer>> relas = buildRelationsForArea(subLevelModels);

        // 全新数据，重新分组排序
        if (relas.size() > 0) {
            Set<Long> relaKeys = relas.keySet();
            for (AreaInfoModel currModel : topLevelModels) {
                areaInfoModels.add(currModel);
                Long pid = currModel.getId();
                if (relaKeys.contains(pid)) {
                    List<Integer> indexs = relas.get(pid);
                    for (Integer index : indexs) {
                        areaInfoModels.add(subLevelModels.get(index));
                    }
                }
            }
        } else {
            areaInfoModels.addAll(topLevelModels);
        }

        return areaInfoModels;
    }

    @Override
    public AreaSumyModel loadAreaTitle(Long deptId, Long deptTypeId) {
        AreaSumyModel areaSumyModel = new AreaSumyModel();
        // 加载部门分类信息
        loadDeptTypeInfo(areaSumyModel, deptTypeId);

        // 加载部门相关信息
        loadDepartmentInfo(areaSumyModel, deptId);

        // 加载部门汇总相关信息
        loadDeptSumyInfo(areaSumyModel);

        // 加载区域面积合计
        loadAreaSummary(areaSumyModel, deptId);

        return areaSumyModel;
    }

    @Override
    public void saveOrUpdateAreaInfo(AreaWithAction areaWithAction) {
        String action = areaWithAction.getAction();
        AreaInfo areaInfo = new AreaInfo();
        BeanUtils.copyProperties(areaWithAction, areaInfo);
        if(action.equals(BusiConst.Action.ADD)){
            saveOrUpdateAreaInfo(areaInfo, true);
        } else if(action.equals(BusiConst.Action.EDIT)){
            saveOrUpdateAreaInfo(areaInfo, false);
        }
    }

    @Override
    public void delAreaInfoByAreaId(Long areaId) {
        // 缓存区域信息
        AreaInfo areaInfo = areaInfoMapper.selectByPrimaryKey(areaId);
        // 删除区域信息
        deptDelService.delAreaByAreaId(areaId);
        // 更新区域所属部门规划值信息
        projDeptInfoService.updateDesignAreaValForDeptOnTime(areaInfo.getDeptId(), areaInfo.getDeptTypeId(), BusiConst.DobuleVal.zeroVal);
    }

    private void saveOrUpdateAreaInfo(AreaInfo areaInfo, boolean saveFlag) {
        if (saveFlag) {
            appendAreaInfoForInsert(areaInfo);

            areaInfoMapper.insert(areaInfo);
        } else {
            areaInfoMapper.updateByPrimaryKeySelective(areaInfo);
        }

        // 取出区域所属部门规划值内容
        Double areaSummary = getAllAreaSummaryByDeptId(areaInfo.getDeptId());

        // 更新区域所属部门规划值信息
        projDeptInfoService.updateDesignAreaValForDeptOnTime(areaInfo.getDeptId(), areaInfo.getDeptTypeId(), areaSummary);
    }

    // 追加区域信息
    private void appendAreaInfoForInsert(AreaInfo areaInfo) {
        areaInfo.setCreateTime(new Date());
    }

    @Override
    public void saveOrUpdateRoomInfo(RoomWithAction roomWithAction) {
        String action = roomWithAction.getAction();
        RoomInfo roomInfo = new RoomInfo();
        BeanUtils.copyProperties(roomWithAction, roomInfo);
        if(action.equals(BusiConst.Action.ADD)){
            saveOrUpdateRoomInfo(roomInfo, true);
        } else if(action.equals(BusiConst.Action.EDIT)){
            saveOrUpdateRoomInfo(roomInfo, false);
        }
    }

    @Override
    public void delRoomInfoByRoomId(Long roomId) {
        // 先缓存当前房间数据
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(roomId);

        // 删除房间数据
        deptDelService.delRoomByRoomId(roomId);

        // 更新所属区域面积合计值
        updateAreaSummaryVal(roomInfo.getAreaId(), roomInfo.getDeptId(), roomInfo.getDeptTypeId());
    }

    @Override
    public void editRoomCntValOnTime(Long roomId, Integer roomCnt) {
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(roomId);
        roomInfo.setCnt(roomCnt);
        autoCaculateAreaSummary(roomInfo);
        saveOrUpdateRoomInfo(roomInfo, false);
    }

    @Override
    public void editRoomAreaValOnTime(Long roomId, Double roomArea) {
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(roomId);
        roomInfo.setAreaTotal(roomArea);
        autoCaculateAreaSummary(roomInfo);
        saveOrUpdateRoomInfo(roomInfo, false);
    }

    private void saveOrUpdateRoomInfo(RoomInfo roomInfo, boolean saveFlag) {
        if (saveFlag) {
            appendRoomInfoForInsert(roomInfo);

            roomInfoMapper.insert(roomInfo);
        } else {
            autoCaculateAreaSummary(roomInfo);

            roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
        }

        // 更新所属区域面积合计值
        updateAreaSummaryVal(roomInfo.getAreaId(), roomInfo.getDeptId(), roomInfo.getDeptTypeId());
    }

    // 追加房间信息
    private void appendRoomInfoForInsert(RoomInfo roomInfo) {
        // 追加创建时间
        roomInfo.setCreateTime(new Date());
        autoCaculateAreaSummary(roomInfo);
    }

    // 设置面积小计
    private void autoCaculateAreaSummary(RoomInfo roomInfo) {
        Integer cnt = roomInfo.getCnt();
        Double areaTotal = roomInfo.getAreaTotal();
        if (null != areaTotal) {
            roomInfo.setAreaSummary(cnt * areaTotal);
        } else {
            roomInfo.setAreaSummary(new Double(BusiConst.DobuleVal.zeroVal));
        }
    }

    private void updateAreaSummaryVal(Long areaId, Long deptId, Long deptTypeId){
        Double areaSummary = getAllAreaSummaryByAreaId(areaId);

        AreaInfo areaInfo = new AreaInfo();
        areaInfo.setId(areaId);
        areaInfo.setDeptId(deptId);
        areaInfo.setDeptTypeId(deptTypeId);
        areaInfo.setAreaSummary(areaSummary);

        saveOrUpdateAreaInfo(areaInfo, false);
    }

    private void loadDeptTypeInfo(AreaSumyModel areaSumyModel, Long deptTypeId) {
        DeptType deptType = deptTypeService.loadDeptTypeByPK(deptTypeId);
        areaSumyModel.setDeptTypeCode(deptType.getDeptCode());
        areaSumyModel.setDeptTypeName(deptType.getDeptName());
        areaSumyModel.setProjId(deptType.getProjId());
    }

    private void loadDepartmentInfo(AreaSumyModel areaSumyModel, Long deptId) {
        Department department = departmentService.loadDepartmentByPK(deptId);
        areaSumyModel.setDeptCode(department.getDeptCode());
        areaSumyModel.setDeptName(department.getDeptName());
        areaSumyModel.setPlanArea(department.getPlanArea());
    }

    private void loadDeptSumyInfo(AreaSumyModel areaSumyModel) {
        DeptSummary deptSummary = deptSumyService.loadDeptSummaryByPK(areaSumyModel.getProjId());
        areaSumyModel.setAreaRatio(deptSummary.getAreaRatio());
    }

    private void loadAreaSummary(AreaSumyModel areaSumyModel, Long deptId) {
        Double areaSummary = getAllAreaSummaryByDeptId(deptId);
        areaSumyModel.setDesignArea(areaSummary);
    }

    private Double getAllAreaSummaryByDeptId(Long deptId){
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("deptId", deptId);
        inParams.put("level", 1);
        PlanAreaHolder planAreaHolder = buildPlanAreaHolder(inParams);
        return planAreaHolder.getPlanAreaTotal();
    }

    private Double getAllAreaSummaryByAreaId(Long areaId){
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("areaId", areaId);
        inParams.put("level", 2);
        PlanAreaHolder planAreaHolder = buildPlanAreaHolder(inParams);
        return planAreaHolder.getPlanAreaTotal();
    }

    private PlanAreaHolder buildPlanAreaHolder(Map<String, Object> inParams){
        List<PlanAreaModel> planAreaModels = areaInfoMapper.loadAllAreaSummaryModel(inParams);
        return new PlanAreaHolder(planAreaModels);
    }

    private Map<Long, List<Integer>> buildRelationsForArea(List<AreaInfoModel> subLevelModels) {
        Map<Long, List<Integer>> relations = new HashMap<>();
        if (CollectionUtils.isNotEmpty(subLevelModels)) {
            for (int i = 0; i < subLevelModels.size(); i++) {
                AreaInfoModel model = subLevelModels.get(i);
                Long pid = model.getPid();
                List<Integer> indexs = relations.get(pid);
                if (CollectionUtils.isEmpty(indexs)) {
                    indexs = new ArrayList<>();
                }
                indexs.add(i);
                relations.put(pid, indexs);
            }
        }
        return relations;
    }
}
