package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.dao.DepartmentMapper;
import com.chilicool.hdtools.dao.DeptSummaryMapper;
import com.chilicool.hdtools.dao.DeptTypeMapper;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.DeptWithAction;
import com.chilicool.hdtools.model.PlanAreaHolder;
import com.chilicool.hdtools.model.PlanAreaModel;
import com.chilicool.hdtools.model.SumyInfoModel;
import com.chilicool.hdtools.service.ProjDeptInfoService;
import com.chilicool.hdtools.service.core.areainfo.AreaSumyService;
import com.chilicool.hdtools.service.core.deptinfo.DepartmentService;
import com.chilicool.hdtools.service.core.deptinfo.DeptDelService;
import com.chilicool.hdtools.service.core.deptinfo.DeptSumyService;
import com.chilicool.hdtools.service.core.deptinfo.DeptTypeService;
import com.chilicool.hdtools.support.DigitalUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chilicool on 2017/10/10.
 */
@Service
public class ProjDeptInfoServiceImpl implements ProjDeptInfoService {
    @Autowired
    private AreaSumyService areaSumyService;
    @Autowired
    private DeptTypeMapper deptTypeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DeptSummaryMapper deptSummaryMapper;
    @Autowired
    private DeptTypeService deptTypeService;
    @Autowired
    private DeptSumyService deptSumyService;
    @Autowired
    private DeptDelService deptDelService;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public boolean ifDeptInfoExist(Long projId) {
        DeptTypeExample example = new DeptTypeExample();
        example.createCriteria().andProjIdEqualTo(projId);
        List<DeptType> deptTypes = deptTypeMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(deptTypes) && deptTypes.size() > 0;
    }

    @Override
    public void initProjDeptType(Long projId) {
        deptTypeService.initDeptTypeInfo(projId);
    }

    @Override
    public void initProjDeptSumy(Long projId) {
        deptSumyService.initProjDeptSumy(projId);
    }

    @Override
    public List<SumyInfoModel> loadAllSumyInfo(Long projId) {
        List<SumyInfoModel> sumyInfoModels = new ArrayList<>();

        Map<String, Object> inParam = new HashMap<>();
        inParam.put("projId", projId);
        inParam.put("level", 1);
        // 先查询父级数据
        List<SumyInfoModel> topLevelModels = deptTypeMapper.loadAllSumyInfoWithLevel(inParam);

        inParam.put("level", 2);
        // 再查询子级数据
        List<SumyInfoModel> subLevelModels = deptTypeMapper.loadAllSumyInfoWithLevel(inParam);
        Map<Long, List<Integer>> relas = buildRelations(subLevelModels);

        // 全新数据，重新分组排序
        if (relas.size() > 0) {
            Set<Long> relaKeys = relas.keySet();
            for (SumyInfoModel currModel : topLevelModels) {
                sumyInfoModels.add(currModel);
                Long pid = currModel.getId();
                if (relaKeys.contains(pid)) {
                    List<Integer> indexs = relas.get(pid);
                    for (Integer index : indexs) {
                        sumyInfoModels.add(subLevelModels.get(index));
                    }
                }
            }
        } else {
            sumyInfoModels.addAll(topLevelModels);
        }

        return sumyInfoModels;
        //return deptTypeMapper.loadAllSumyInfo(projId);
    }

    @Override
    public DeptSummary loadDeptSummaryInfo(Long projId) {
        DeptSummaryExample example = buildDeptSummaryExampleWithProjId(projId);
        List<DeptSummary> deptSummaries = deptSummaryMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(deptSummaries) ? deptSummaries.get(0) : new DeptSummary();
    }

    @Override
    public List<DeptType> loadDeptTypeByProjId(long projId) {
        DeptTypeExample example = new DeptTypeExample();
        example.createCriteria().andProjIdEqualTo(projId);
        return deptTypeMapper.selectByExample(example);
    }

    @Override
    public Long saveDeptSummaryInfo(DeptSummary deptSummary) {
        deptSummaryMapper.insert(deptSummary);

        return deptSummary.getId();
    }

    private Map<Long, List<Integer>> buildRelations(List<SumyInfoModel> subLevelModels) {
        Map<Long, List<Integer>> relations = new HashMap<>();
        if (CollectionUtils.isNotEmpty(subLevelModels)) {
            for (int i = 0; i < subLevelModels.size(); i++) {
                SumyInfoModel model = subLevelModels.get(i);
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

    @Override
    public void saveOrUpdateDepartmentInfo(DeptWithAction departmentWithAction) {
        //        // 新增加一个部门信息
        //        appendDepartmentInfoForInsert(department);
        //        departmentMapper.insert(department);
        //
        //        // 获取部门分类编号
        //        Long deptTypeId = getDeptTypeIdByDeptId(department.getId());
        //
        //        // 更新部门分类规划面积
        //        updatePlanAreaValWithRule(deptTypeId);
        String action = departmentWithAction.getAction();
        Department department = new Department();
        BeanUtils.copyProperties(departmentWithAction, department);
        if(action.equals(BusiConst.Action.ADD)){
            saveOrUpdatePlanAreaValForDeptOnTime(department, true);
        } else if(action.equals(BusiConst.Action.EDIT)){
            saveOrUpdatePlanAreaValForDeptOnTime(department, false);
        }
    }

    // 追加部门信息
    private void appendDepartmentInfoForInsert(Department department) {
        department.setCreateTime(new Date());
        department.setDesignArea(BusiConst.DobuleVal.zeroVal);

        Double planArea = department.getPlanArea();
        if (null == planArea) {
            department.setPlanArea(BusiConst.DobuleVal.zeroVal);
        }
    }

    // 使用部门分类编号检查并且更新部门分类规划面积
    private boolean updatePlanAreaValWithRule(Long deptTypeId) {
        boolean ifUpdateDeptTypeFlag = false;
        // 获取同一部门分类的所有部门信息-规划值
        List<PlanAreaModel> planAreaModels = departmentMapper.loadAllDeptInfoModel(deptTypeId);
        PlanAreaHolder planAreaHolder = new PlanAreaHolder(planAreaModels);

        // 获取部门分类信息
        DeptType deptType = loadDeptTypeWithPK(deptTypeId);
        Double planAreaSumy = deptType.getPlanArea();
        Double planAreaTotal = planAreaHolder.getPlanAreaTotal();

        // 部门信息规划面积发生变更，并且同一部门分类下的部门规划面积之和大于部门分类信息的规划面积，则进行更新；
        if (planAreaTotal > planAreaSumy) {
            updatePlanAreaValOnTime(deptTypeId, planAreaTotal);

            ifUpdateDeptTypeFlag = true;
        }
        return ifUpdateDeptTypeFlag;
    }

    private void updateDesignAreaValOnTime(Long deptTypeId){
        // 获取同一部门分类的所有部门信息-设计值
        List<PlanAreaModel> planAreaModels = departmentMapper.loadAllDeptDesignAreaInfoModel(deptTypeId);
        PlanAreaHolder planAreaHolder = new PlanAreaHolder(planAreaModels);
        // 获取设计值总和
        Double planAreaTotal = planAreaHolder.getPlanAreaTotal();
        // 更新部门分类的设计面积值
        updateDesignAreaValOnTime(deptTypeId, planAreaTotal);
    }

    @Override
    public void updatePlanAreaValOnTime(Long deptTypeId, Double planAreaVal) {
        // 更新部门分类规划面积值
        updatePlanAreaValByPK(deptTypeId, planAreaVal);

        // 获取项目编号
        Long projId = getProjIdByDeptTypeId(deptTypeId);

       // 更新部门分类规划面积占比
        PlanAreaHolder planAreaHolder = updateDeptTypePlanAreaPersent(projId);

        // 更新部门分类汇总设计面积总计
        updateDeptSumyPlanAreaValByProjId(projId, planAreaHolder.getPlanAreaTotal());
    }

    @Override
    public void updateDesignAreaValOnTime(Long deptTypeId, Double designAreaVal) {
        // 更新部门分类设计面积值
        updateDesignAreaValByPK(deptTypeId, designAreaVal);

        // 获取项目编号
        Long projId = getProjIdByDeptTypeId(deptTypeId);

        // 更新部门分类设计面积占比
        PlanAreaHolder planAreaHolder = updateDeptTypeDesignAreaPersent(projId);

        // 更新部门分类汇总设计面积总计
        updateDeptSumyDesignAreaValByProjId(projId, planAreaHolder.getPlanAreaTotal());
    }

    @Override
    public boolean updatePlanAreaValForDeptOnTime(Long deptId, Double planAreaVal) {
        Department department = new Department();
        department.setId(deptId);
        department.setPlanArea(planAreaVal);
        return saveOrUpdatePlanAreaValForDeptOnTime(department, false);
    }

    @Override
    public void delDeptInfoByDeptId(Long deptId) {
        // 删除部门信息
        // departmentMapper.deleteByPrimaryKey(deptId);
        deptDelService.delDeptByDeptId(deptId);
    }

    @Override
    public void editSumyAreaRatioValOnTime(Long sumyId, Double areaRatio) {
        if (null != areaRatio) {
            DeptSummary deptSummary = deptSummaryMapper.selectByPrimaryKey(sumyId);
            if (null != deptSummary) {
                deptSummary.setPlanAreaRatio(areaRatio);

                Double areaSummary = deptSummary.getPlanAreaSummary();
                if (null == areaSummary) {
                    areaSummary = BusiConst.DobuleVal.zeroVal;
                    deptSummary.setPlanAreaSummary(areaSummary);
                }

                deptSummary.setPlanAreaTotal(areaSummary * areaRatio);
                updateDeptSummaryByPk(deptSummary);
            }
        }
    }

    @Override
    public void editSumyDesignAreaRatioValOnTime(Long sumyId, Double areaRatio) {
        if (null != areaRatio) {
            DeptSummary deptSummary = deptSummaryMapper.selectByPrimaryKey(sumyId);
            if (null != deptSummary) {
                deptSummary.setDesignAreaRatio(areaRatio);

                Double areaSummary = deptSummary.getDesignAreaSummary();
                if (null == areaSummary) {
                    areaSummary = BusiConst.DobuleVal.zeroVal;
                    deptSummary.setDesignAreaSummary(areaSummary);
                }

                deptSummary.setDesignAreaTotal(areaSummary * areaRatio);
                updateDeptSummaryByPk(deptSummary);
            }
        }
    }

    @Override
    public void editSumyNoteValOnTime(Long sumyId, String note) {
        if (StringUtils.isNotEmpty(note)) {
            DeptSummary deptSummary = new DeptSummary();
            deptSummary.setId(sumyId);
            deptSummary.setNote(note);
            updateDeptSummaryByPk(deptSummary);
        }
    }

    private void updateDeptSummaryByPk(DeptSummary deptSummary) {
        deptSummaryMapper.updateByPrimaryKeySelective(deptSummary);
    }

    private boolean saveOrUpdatePlanAreaValForDeptOnTime(Department department, boolean saveFlag) {
        if (saveFlag) {
            // 新增加一个部门信息
            appendDepartmentInfoForInsert(department);
            departmentMapper.insert(department);
        } else {
            // 更新部门信息规划面积值
            updateDeptInfoByPK(department);
        }

        // 部门规划面积更新同步更新到区域汇总信息的规划面积
        updateAreaSummaryPlanAreaTotalOnTime(department);

        // 获取部门分类编号
        Long deptTypeId = getDeptTypeIdByDeptId(department.getId());

        // 更新部门分类规划面积
        return updatePlanAreaValWithRule(deptTypeId);
    }

    // 更新区域汇总信息
    private void updateAreaSummaryPlanAreaTotalOnTime(Department department) {
        AreaSummary areaSummary = areaSumyService.loadAreaSummaryByDeptId(department.getId());
        if (null != areaSummary && null != areaSummary.getDeptId() && areaSummary.getDeptId() != 0) {
            Double planAreaTotal = department.getPlanArea();
            areaSummary.setPlanAreaTotal(planAreaTotal);

            Double planAreaRatio = areaSummary.getPlanAreaRatio();
            if (!BusiConst.DobuleVal.zeroVal.equals(planAreaRatio)) {
                areaSummary.setPlanAreaSummary(planAreaTotal / planAreaRatio);
            }

            areaSumyService.updateAreaSummaryByPK(areaSummary);
        }
    }

    /**
     * 更新部门设计面积信息
     *
     * @param department
     */
    @Override
    public void updateDesignAreaValForDeptOnTime(Department department) {
        updateDeptInfoByPK(department);

        // 获取部门分类编号
        Long deptTypeId = department.getDeptTypeId();
        if (null == deptTypeId || 0L == deptTypeId) {
            deptTypeId = getDeptTypeIdByDeptId(department.getId());
        }

        // 更新部门分类设计面积
        updateDesignAreaValOnTime(deptTypeId);
    }

    @Override
    public void updateDesignAreaValForDeptOnTime(Long deptId, Long deptTypeId, Double designArea) {
        Department department = new Department();
        department.setId(deptId);
        department.setDeptTypeId(deptTypeId);
        department.setDesignArea(designArea);
        updateDesignAreaValForDeptOnTime(department);
    }

    @Override
    public boolean deptCodeExist(Long deptTypeId, Short orderIdx) {
        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andDeptTypeIdEqualTo(deptTypeId).andOrderIdxEqualTo(orderIdx);
        List<Department> departments = departmentMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(departments) && departments.size() > 0;
    }

    @Override
    public Short getNextDeptCode(Long deptTypeId) {
        Short nextDeptCode = departmentMapper.getNextDeptCode(deptTypeId);
        if(null == nextDeptCode || 0 == nextDeptCode){
            nextDeptCode = 1;
        } else {
            nextDeptCode++;
        }
        return nextDeptCode;
    }

    @Override
    public void saveDeptTypeInfo(DeptType deptType) {
        deptTypeService.saveDeptTypeInfo(deptType);
    }

    @Override
    public List<Department> loadAllDepartmentByDeptTypeId(Long deptTypeId) {
        return departmentService.loadAllDepartmentByDeptTypeId(deptTypeId);
    }

    @Override
    public void saveDepartment(Department department) {
        departmentService.saveDepartment(department);
    }

    /**
     * 使用部门分类编号获取项目编号
     *
     * @param deptTypeId
     * @return
     */
    private Long getProjIdByDeptTypeId(Long deptTypeId) {
        DeptType deptType = loadDeptTypeWithPK(deptTypeId);
        return null != deptType ? deptType.getProjId() : 0L;
    }

    /**
     * 使用部门编号获取部门分类编号
     *
     * @param deptId
     * @return
     */
    private Long getDeptTypeIdByDeptId(Long deptId) {
        Department department = departmentMapper.selectByPrimaryKey(deptId);
        return null != department ? department.getDeptTypeId() : 0L;
    }

    /**
     * 更新部门分类规划面积值
     *
     * @param deptTypeId
     * @param planAreaVal
     */
    private void updatePlanAreaValByPK(Long deptTypeId, Double planAreaVal) {
        DeptType deptType = new DeptType();
        deptType.setId(deptTypeId);
        deptType.setPlanArea(planAreaVal);
        deptTypeMapper.updateByPrimaryKeySelective(deptType);
    }

    /**
     * 更新部门分类规划面积值
     *
     * @param deptTypeId
     * @param designAreaVal
     */
    private void updateDesignAreaValByPK(Long deptTypeId, Double designAreaVal) {
        DeptType deptType = new DeptType();
        deptType.setId(deptTypeId);
        deptType.setDesignArea(designAreaVal);
        deptTypeMapper.updateByPrimaryKeySelective(deptType);
    }

    /**
     * 更新部门信息规划面积值
     *
     * @param department
     */
    private void updateDeptInfoByPK(Department department) {
        departmentMapper.updateByPrimaryKeySelective(department);
    }

    /**
     * 更新部门分类规划面积占比
     *
     * @param deptTypeId
     * @param persentVal
     */
    private void updatePlanAreaPersentByPK(Long deptTypeId, Double persentVal){
        DeptType deptType = new DeptType();
        deptType.setId(deptTypeId);
        deptType.setPlanAreaPersent(persentVal);
        deptTypeMapper.updateByPrimaryKeySelective(deptType);
    }

    /**
     * 更新部门分类设计面积占比
     *
     * @param deptTypeId
     * @param persentVal
     */
    private void updateDesignAreaPersentByPK(Long deptTypeId, Double persentVal) {
        DeptType deptType = new DeptType();
        deptType.setId(deptTypeId);
        deptType.setDesignAreaPersent(persentVal);
        deptTypeMapper.updateByPrimaryKeySelective(deptType);
    }

    /**
     * 更新全部部门分类规划面积占比
     *
     * @param projId
     */
    private PlanAreaHolder updateDeptTypePlanAreaPersent(Long projId) {
        List<PlanAreaModel> planAreaModels = deptTypeMapper.loadAllDeptModel(projId);
        PlanAreaHolder planAreaHolder = new PlanAreaHolder(planAreaModels);
        List<Long> deptTypeIds = planAreaHolder.getDeptTypeIdList();
        if (CollectionUtils.isNotEmpty(deptTypeIds)) {
            for (Long deptTypeId : deptTypeIds) {
                updatePlanAreaPersentByPK(deptTypeId, planAreaHolder.getPersentByDeptTypeId(deptTypeId));
            }
        }
        return planAreaHolder;
    }

    /**
     * 更新全部部门分类规划面积占比
     *
     * @param projId
     */
    private PlanAreaHolder updateDeptTypeDesignAreaPersent(Long projId) {
        List<PlanAreaModel> planAreaModels = deptTypeMapper.loadAllDeptDesignAreaModel(projId);
        PlanAreaHolder planAreaHolder = new PlanAreaHolder(planAreaModels);
        List<Long> deptTypeIds = planAreaHolder.getDeptTypeIdList();
        if (CollectionUtils.isNotEmpty(deptTypeIds)) {
            for (Long deptTypeId : deptTypeIds) {
                updateDesignAreaPersentByPK(deptTypeId, planAreaHolder.getPersentByDeptTypeId(deptTypeId));
            }
        }
        return planAreaHolder;
    }

    /**
     * 更新部门汇总规划面积值
     *
     * @param projId
     * @param persentVal
     */
    private void updateDeptSumyPlanAreaValByProjId(Long projId, Double persentVal) {
        DeptSummary deptSummary = loadDeptSummaryInfoByProjId(projId);
        Double planAreaRatio = deptSummary.getPlanAreaRatio();
        if (null == planAreaRatio) {
            planAreaRatio = BusiConst.DobuleVal.oneVal;
            deptSummary.setPlanAreaRatio(planAreaRatio);
        }
        deptSummary.setPlanAreaSummary(persentVal);
        // format to 2 bit
        //deptSummary.setPlanAreaTotal(planAreaRatio * persentVal);
        deptSummary.setPlanAreaTotal(DigitalUtil.f2bit(planAreaRatio * persentVal));
        deptSummaryMapper.updateByPrimaryKeySelective(deptSummary);
    }

    // 使用项目编号查询部门汇总信息
    private DeptSummary loadDeptSummaryInfoByProjId(Long projId) {
        DeptSummaryExample example = buildDeptSummaryExampleWithProjId(projId);
        List<DeptSummary> deptSummaries = deptSummaryMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(deptSummaries) ? deptSummaries.get(0) : (new DeptSummary());
    }

    /**
     * 更新部门汇总设计面积值
     *
     * @param projId
     * @param persentVal
     */
    private void updateDeptSumyDesignAreaValByProjId(Long projId, Double persentVal) {
//        DeptSummary deptSummary = new DeptSummary();
//        deptSummary.setProjId(projId);
//        deptSummary.setDesignAreaTotal(persentVal);
//
//        DeptSummaryExample example = buildDeptSummaryExampleWithProjId(projId);
//        deptSummaryMapper.updateByExampleSelective(deptSummary, example);

        DeptSummary deptSummary = loadDeptSummaryInfoByProjId(projId);
        Double designAreaRatio = deptSummary.getDesignAreaRatio();
        if (null == designAreaRatio) {
            designAreaRatio = BusiConst.DobuleVal.oneVal;
            deptSummary.setPlanAreaRatio(designAreaRatio);
        }
        deptSummary.setDesignAreaSummary(persentVal);
        // deptSummary.setDesignAreaTotal(designAreaRatio * persentVal);
        deptSummary.setDesignAreaTotal(DigitalUtil.f2bit(designAreaRatio * persentVal));
        deptSummaryMapper.updateByPrimaryKeySelective(deptSummary);
    }

    private DeptSummaryExample buildDeptSummaryExampleWithProjId(Long projId){
        DeptSummaryExample example = new DeptSummaryExample();
        example.createCriteria().andProjIdEqualTo(projId);
        return example;
    }

    private DeptType loadDeptTypeWithPK(Long deptTypeId) {
        return deptTypeMapper.selectByPrimaryKey(deptTypeId);
    }
}
