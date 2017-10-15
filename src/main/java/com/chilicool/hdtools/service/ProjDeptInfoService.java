package com.chilicool.hdtools.service;

import com.chilicool.hdtools.domain.Department;
import com.chilicool.hdtools.domain.DeptSummary;
import com.chilicool.hdtools.model.DeptWithAction;
import com.chilicool.hdtools.model.ResultBase;
import com.chilicool.hdtools.model.SumyInfoModel;

import java.util.List;

/**
 * Created by chilicool on 2017/10/10.
 */
public interface ProjDeptInfoService {
    /**
     * 初始化部门分类信息
     *
     * @param projId
     */
    public void initProjDeptType(Long projId);

    /**
     * 初始化部门分类信息
     *
     * @param projId
     */
    public void initProjDeptSumy(Long projId);

    /**
     * 加载所有部门信息，含一级二级
     *
     * @param projId
     * @return
     */
    public List<SumyInfoModel> loadAllSumyInfo(Long projId);

    /**
     * 加载部门汇总信息（表格头部的汇总数据）
     *
     * @param projId
     * @return
     */
    public DeptSummary loadDeptSummaryInfo(Long projId);

    /**
     * 保存部门信息
     * - 同时检查部门分类规划面积是否刷新
     * - 刷新规则：增加当前部门规划面积之后，部门分类规划面积汇总值超过原设定规划值
     *
     * @param department
     */
    public void saveOrUpdateDepartmentInfo(DeptWithAction department);

    /**
     * 更新部门分类规划面积
     * - 同时刷新全部部门分类规划面积占比
     * - 同时刷新部门分类汇总规划面积和规划面积占比
     *
     * @param deptTypeId
     * @param planAreaVal
     */
    public void updatePlanAreaValOnTime(Long deptTypeId, Double planAreaVal);

    /**
     * 更新部门分类设计面积
     * - 同时刷新全部部门分类规划面积占比
     * - 同时刷新部门分类汇总规划面积和规划面积占比
     *
     * @param deptTypeId
     * @param designAreaVal
     */
    public void updateDesignAreaValOnTime(Long deptTypeId, Double designAreaVal);

    /**
     * 更新部门信息规划面积
     * - 判断当前更新是否会导致部门分类规划面积发生变更；变更则执行以下逻辑
     * - 同时刷新全部部门分类规划面积占比
     * - 同时刷新部门分类汇总规划面积和规划面积占比
     *
     * @param deptId
     * @param planAreaVal
     */
    public boolean updatePlanAreaValForDeptOnTime(Long deptId, Double planAreaVal);

    /**
     * 删除部门信息
     * - 当前操作不会影响部门分类信息
     * - 当前操作影响部门下的区域以及房间信息
     *
     * @param deptId
     */
    public void delDeptInfoByDeptId(Long deptId);

    /**
     * 实时更新面积系数
     *
     * @param sumyId
     * @param areaRatio
     */
    public void editSumyAreaRatioValOnTime(Long sumyId, Double areaRatio);

    /**
     * 实时更新备注信息
     *
     * @param sumyId
     * @param note
     */
    public void editSumyNoteValOnTime(Long sumyId, String note);

    /**
     * 更新部门信息的设计面积值
     * - 自下而上发起，由区域面积变更或者房间面积变更引起改变
     *
     * @param department
     */
    public void updateDesignAreaValForDeptOnTime(Department department);

    /**
     * 更新部门信息的设计面积值
     * - 自下而上发起，由区域面积变更或者房间面积变更引起改变
     *
     * @param deptId
     * @param deptTypeId
     * @param designArea
     */
    public void updateDesignAreaValForDeptOnTime(Long deptId, Long deptTypeId, Double designArea);
}
