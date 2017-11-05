package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.dao.AreaInfoMapper;
import com.chilicool.hdtools.dao.AreaSummaryMapper;
import com.chilicool.hdtools.dao.RoomInfoMapper;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.*;
import com.chilicool.hdtools.service.ProjAreaInfoService;
import com.chilicool.hdtools.service.ProjDeptInfoService;
import com.chilicool.hdtools.service.busi.AreaSummaryService;
import com.chilicool.hdtools.service.core.areainfo.AreaInfoService;
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
    private AreaSummaryMapper areaSummaryMapper;
    @Autowired
    private AreaSummaryService areaSummaryService;
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
    @Autowired
    private AreaInfoService areaInfoService;

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
    public AreaSumyModel loadAreaSummary(Long deptId, Long deptTypeId) {
        AreaSumyModel areaSumyModel = new AreaSumyModel();

        // 加载部门分类信息
        loadDeptTypeInfo(areaSumyModel, deptTypeId);
        // 加载部门相关信息
        loadDepartmentInfo(areaSumyModel, deptId);
        // 加载区域汇总信息
        loadAreaSummary(areaSumyModel, deptId);

        return areaSumyModel;
    }

    @Override
    @Deprecated
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
        if (action.equals(BusiConst.Action.ADD)) {
            saveOrUpdateAreaInfo(areaInfo, true);
        } else if (action.equals(BusiConst.Action.EDIT)) {
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

        // 取出区域所属部门设计值内容
        Double areaSummaryVal = getAllAreaSummaryByDeptId(areaInfo.getDeptId());

        // 更新区域汇总信息
        AreaSummary areaSummary = initOrUpdateAreaSumaryInfo(areaInfo, areaSummaryVal);

        // 更新区域所属部门设计值信息
        Double areaTotalVal = areaSummary.getDesignAreaTotal();
        projDeptInfoService.updateDesignAreaValForDeptOnTime(areaInfo.getDeptId(), areaInfo.getDeptTypeId(), areaTotalVal);
    }

    private AreaSummary initOrUpdateAreaSumaryInfo(AreaInfo areaInfo, Double areaSummaryVal) {
        AreaSummary areaSummary = areaSummaryService.loadAreaSummaryByDeptId(areaInfo.getDeptId());

        boolean areaSummaryExist = (null != areaSummary && null != areaSummary.getDeptId() && 0 != areaSummary.getDeptId());
        if (!areaSummaryExist) {
            areaSummary = genearteAreaSummaryInfo(areaInfo, areaSummaryVal);

            areaSummaryMapper.insert(areaSummary);
        } else {
            Double designAreaRatio = areaSummary.getDesignAreaRatio();
            if (null == designAreaRatio) {
                designAreaRatio = BusiConst.DobuleVal.oneVal;
                areaSummary.setDesignAreaRatio(designAreaRatio);
            }
            // 更新设计面积小计、总计
            areaSummary.setDesignAreaSummary(areaSummaryVal);
            areaSummary.setDesignAreaTotal(areaSummaryVal * designAreaRatio);

            // 更新规划面积小计、总计
            autoCaculateAreaSummaryPlanAreaInfo(areaSummary);

            areaSummaryMapper.updateByPrimaryKeySelective(areaSummary);
        }

        return areaSummary;
    }

    // 初始化区域汇总信息
    private AreaSummary genearteAreaSummaryInfo(AreaInfo areaInfo, Double areaSummaryVal) {
        AreaSummary areaSummary = new AreaSummary();
        BeanUtils.copyProperties(areaInfo, areaSummary);
        // 重置编号
        areaSummary.setId(null);
        // 设置创建时间
        Date currTime = new Date();
        areaSummary.setCreateTime(currTime);
        areaSummary.setUpdateTime(currTime);
        // 设置面积系数
        areaSummary.setPlanAreaRatio(BusiConst.DobuleVal.oneVal);
        areaSummary.setDesignAreaRatio(BusiConst.DobuleVal.oneVal);
        // 设置设计面积小计、总计
        areaSummary.setDesignAreaSummary(areaSummaryVal);
        areaSummary.setDesignAreaTotal(areaSummaryVal);
        // 设置规划面积小计、总计
        autoCaculateAreaSummaryPlanAreaInfo(areaSummary);
        return areaSummary;
    }

    // 自动计算并且更新规则面积值
    private void autoCaculateAreaSummaryPlanAreaInfo(AreaSummary areaSummary) {
        Department department = departmentService.loadDepartmentByPK(areaSummary.getDeptId());
        Double planAreaTotal = department.getPlanArea();
        areaSummary.setPlanAreaTotal(planAreaTotal);

        Double planAreaRatio = areaSummary.getPlanAreaRatio();
        if (null == planAreaRatio) {
            planAreaRatio = BusiConst.DobuleVal.oneVal;
            areaSummary.setPlanAreaRatio(planAreaRatio);
        }

        if (!BusiConst.DobuleVal.zeroVal.equals(planAreaRatio)) {
            areaSummary.setPlanAreaSummary(planAreaTotal / planAreaRatio);
        }
    }

    // 使用部门编号判断区域汇总信息是否存在
    private boolean checkAreaSummaryExistByDeptId(Long deptId) {
        AreaSummary areaSummary = areaSummaryService.loadAreaSummaryByDeptId(deptId);
        return null != areaSummary && null != areaSummary.getDeptId() && 0 != areaSummary.getDeptId();
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
        if (action.equals(BusiConst.Action.ADD)) {
            saveOrUpdateRoomInfo(roomInfo, true);
        } else if (action.equals(BusiConst.Action.EDIT)) {
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
        updateAreaTotalVal(roomInfo.getAreaId(), roomInfo.getDeptId(), roomInfo.getDeptTypeId());
    }

    @Override
    public void editRoomCntValOnTime(Long roomId, Integer roomCnt) {
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(roomId);
        roomInfo.setDesignCnt(roomCnt);
        autoCaculateAreaTotal(roomInfo);
        saveOrUpdateRoomInfo(roomInfo, false);
    }

    @Override
    public void editRoomAreaValOnTime(Long roomId, Double roomArea) {
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(roomId);
        roomInfo.setDesignAreaSummary(roomArea);
        autoCaculateAreaTotal(roomInfo);
        saveOrUpdateRoomInfo(roomInfo, false);
    }

    @Override
    public void editDeptPlanAreaRatioValOnTime(Long areaSumyId, Double areaRatio) {
        if (null != areaRatio) {
            AreaSummary areaSummary = areaSummaryMapper.selectByPrimaryKey(areaSumyId);
            if (null != areaSummary) {
                areaSummary.setPlanAreaRatio(areaRatio);

                Double areaTotalVal = areaSummary.getPlanAreaTotal();
                if (null == areaTotalVal) {
                    Department department = departmentService.loadDepartmentByPK(areaSummary.getDeptId());
                    areaTotalVal = department.getPlanArea();
                    areaSummary.setPlanAreaTotal(areaTotalVal);
                }

                if (!BusiConst.DobuleVal.zeroVal.equals(areaRatio)) {
                    areaSummary.setPlanAreaSummary(areaTotalVal / areaRatio);
                }
                // 自上而下更新，到此即止
                updateAreaSummaryByPk(areaSummary);
            }
        }
    }

    @Override
    public void editDeptDesignAreaRatioValOnTime(Long areaSumyId, Double areaRatio) {
        if (null != areaRatio) {
            AreaSummary areaSummary = areaSummaryMapper.selectByPrimaryKey(areaSumyId);
            if (null != areaSummary) {
                areaSummary.setDesignAreaRatio(areaRatio);

                Double areaSummaryVal = areaSummary.getDesignAreaSummary();
                if (null == areaSummaryVal) {
                    areaSummaryVal = BusiConst.DobuleVal.zeroVal;
                    areaSummary.setDesignAreaSummary(areaSummaryVal);
                }
                areaSummary.setDesignAreaTotal(areaSummaryVal * areaRatio);

                // 更新区域汇总设计值信息
                updateAreaSummaryByPk(areaSummary);
                // 更新区域所属部门设计值信息
                Double areaTotalVal = areaSummary.getDesignAreaTotal();
                projDeptInfoService.updateDesignAreaValForDeptOnTime(areaSummary.getDeptId(),
                        areaSummary.getDeptTypeId(), areaTotalVal);
            }
        }
    }

    @Override
    public String getNextAreaCodeByDeptId(Long deptId) {
        String nextAreaCode = "";
        if (null != deptId && 0 != deptId) {
            Department department = departmentService.loadDepartmentByPK(deptId);
            Short nextAreaId = areaInfoMapper.getMaxAreaCodeByDeptId(deptId);

            nextAreaCode = department.getDeptCode();
            nextAreaCode += BusiConst.CONST_CODE_SEPRATOR;

            if (null == nextAreaId || 0 == nextAreaId) {
                nextAreaCode += "01";
            } else {
                nextAreaId++;
                nextAreaCode += (nextAreaId < 10) ? "0" + nextAreaId : nextAreaId;
            }
        }
        return nextAreaCode;
    }

    @Override
    public boolean areaCodeExist(Long deptId, Short orderIdx) {
        AreaInfoExample example = new AreaInfoExample();
        example.createCriteria().andDeptIdEqualTo(deptId).andOrderIdxEqualTo(orderIdx);
        List<AreaInfo> areaInfos = areaInfoMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(areaInfos) && areaInfos.size() > 0;
    }

    @Override
    public String getNextRoomCodeByAreaId(Long areaId) {
        String nextRoomCode = "";
        if (null != areaId && 0 != areaId) {
            AreaInfo areaInfo = areaInfoService.loadAreaInfoByPK(areaId);
            Short nextRoomId = roomInfoMapper.getMaxRoomCodeByAreaId(areaId);

            nextRoomCode = areaInfo.getOfficeCode();
            nextRoomCode += BusiConst.CONST_CODE_SEPRATOR;

            if (null == nextRoomId || 0 == nextRoomId) {
                nextRoomCode += "01";
            } else {
                nextRoomId++;
                nextRoomCode += (nextRoomId < 10) ? "0" + nextRoomId : nextRoomId;
            }
        }
        return nextRoomCode;
    }

    @Override
    public boolean roomCodeExist(Long areaId, Short orderIdx) {
        RoomInfoExample example = new RoomInfoExample();
        example.createCriteria().andAreaIdEqualTo(areaId).andOrderIdxEqualTo(orderIdx);
        List<RoomInfo> roomInfos = roomInfoMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(roomInfos) && roomInfos.size() > 0;
    }

    private void updateAreaSummaryByPk(AreaSummary areaSummary) {
        areaSummaryMapper.updateByPrimaryKeySelective(areaSummary);
    }

    private void saveOrUpdateRoomInfo(RoomInfo roomInfo, boolean saveFlag) {
        if (saveFlag) {
            appendRoomInfoForInsert(roomInfo);

            roomInfoMapper.insert(roomInfo);
        } else {
            autoCaculateAreaTotal(roomInfo);

            roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
        }

        // 更新所属区域面积合计值
        updateAreaTotalVal(roomInfo.getAreaId(), roomInfo.getDeptId(), roomInfo.getDeptTypeId());
    }

    // 追加房间信息
    private void appendRoomInfoForInsert(RoomInfo roomInfo) {
        // 追加创建时间
        roomInfo.setCreateTime(new Date());
        autoCaculateAreaTotal(roomInfo);
        // 清空房间编号
        roomInfo.setId(null);
    }

    // 设置面积小计
    private void autoCaculateAreaTotal(RoomInfo roomInfo) {
        Integer cnt = roomInfo.getDesignCnt();
        Double areaSummary = roomInfo.getDesignAreaSummary();
        if (null != areaSummary) {
            roomInfo.setDesignAreaTotal(cnt * areaSummary);
        } else {
            roomInfo.setDesignAreaTotal(new Double(BusiConst.DobuleVal.zeroVal));
        }
    }

    private void updateAreaTotalVal(Long areaId, Long deptId, Long deptTypeId) {
        Double areaTotal = getAllDesignAreaTotalByAreaId(areaId);

        AreaInfo areaInfo = new AreaInfo();
        areaInfo.setId(areaId);
        areaInfo.setDeptId(deptId);
        areaInfo.setDeptTypeId(deptTypeId);
        areaInfo.setDesignAreaTotal(areaTotal);

        saveOrUpdateAreaInfo(areaInfo, false);
    }

    // 加载部门分类信息
    private void loadDeptTypeInfo(AreaSumyModel areaSumyModel, Long deptTypeId) {
        DeptType deptType = deptTypeService.loadDeptTypeByPK(deptTypeId);
        areaSumyModel.setDeptTypeCode(deptType.getDeptCode());
        areaSumyModel.setDeptTypeName(deptType.getDeptName());
        areaSumyModel.setProjId(deptType.getProjId());
    }

    // 加载部门信息
    private void loadDepartmentInfo(AreaSumyModel areaSumyModel, Long deptId) {
        Department department = departmentService.loadDepartmentByPK(deptId);
        areaSumyModel.setDeptCode(department.getDeptCode());
        areaSumyModel.setDeptName(department.getDeptName());
        // areaSumyModel.setPlanArea(department.getPlanArea());
    }

    // 加载部门汇总信息【为了查面积系数】
    @Deprecated
    private void loadDeptSumyInfo(AreaSumyModel areaSumyModel) {
        DeptSummary deptSummary = deptSumyService.loadDeptSummaryByPK(areaSumyModel.getProjId());
        // areaSumyModel.setAreaRatio(deptSummary.getPlanAreaRatio());
    }

    // 加载区域汇总信息
    private void loadAreaSummary(AreaSumyModel areaSumyModel, Long deptId) {
        // Double areaSummary = getAllAreaSummaryByDeptId(deptId);
        // areaSumyModel.setDesignArea(areaSummary);
        AreaSummary areaSummary = areaSummaryService.loadAreaSummaryByDeptId(deptId);
        if (areaSummary.getDeptId() != null) {
            BeanUtils.copyProperties(areaSummary, areaSumyModel);
        }
    }

    private Double getAllAreaSummaryByDeptId(Long deptId) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("deptId", deptId);
        inParams.put("level", 1);
        PlanAreaHolder planAreaHolder = buildPlanAreaHolder(inParams);
        return planAreaHolder.getPlanAreaTotal();
    }

    private Double getAllDesignAreaTotalByAreaId(Long areaId) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("areaId", areaId);
        inParams.put("level", 2);
        PlanAreaHolder planAreaHolder = buildPlanAreaHolder(inParams);
        return planAreaHolder.getPlanAreaTotal();
    }

    private PlanAreaHolder buildPlanAreaHolder(Map<String, Object> inParams) {
        List<PlanAreaModel> planAreaModels = areaInfoMapper.loadAllDesignAreaTotalModel(inParams);
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
