package com.chilicool.hdtools.service.core.deptinfo;

import com.chilicool.hdtools.domain.DeptSummary;
import com.chilicool.hdtools.domain.DeptType;

/**
 * Created by chilicool on 2017/10/14.
 */
public interface DeptSumyService {
    /**
     * 初始化部门汇总信息
     *
     * @param projId
     */
    public void initProjDeptSumy(Long projId);

    /**
     * 获取部门分类信息
     *
     * @param projId
     * @return
     */
    public DeptSummary loadDeptSummaryByPK(Long projId);
}
