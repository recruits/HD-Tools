package com.chilicool.hdtools.service.busi;

import com.chilicool.hdtools.domain.AreaSummary;

/**
 * Created by chilicool on 2017/10/22.
 */
public interface AreaSummaryService {
    /**
     * 使用部门编号加载区域汇总信息
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
}
