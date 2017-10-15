package com.chilicool.hdtools.service.core.deptinfo;

import com.chilicool.hdtools.domain.Department;

/**
 * Created by chilicool on 2017/10/15.
 */
public interface DepartmentService {
    /**
     * 获取部门信息
     *
     * @param deptId
     * @return
     */
    public Department loadDepartmentByPK(Long deptId);
}
