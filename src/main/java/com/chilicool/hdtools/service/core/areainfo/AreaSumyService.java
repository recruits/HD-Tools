package com.chilicool.hdtools.service.core.areainfo;

import com.chilicool.hdtools.domain.AreaSummary;

/**
 * Created by zechenzhang on 2017/11/12.
 */
public interface AreaSumyService {

    /**
     * 查询部门的区域汇总信息
     *
     * @param deptId
     * @return
     */
    public AreaSummary loadAreaSummaryByDeptId(Long deptId);

    /**
     * 更新区域汇总信息
     *
     * @param areaSummary
     */
    public void updateAreaSummaryByPK(AreaSummary areaSummary);

    /**
     * 保存区域汇总信息
     *
     * @param areaSummary
     */
    public void saveAreaSummary(AreaSummary areaSummary);
}
