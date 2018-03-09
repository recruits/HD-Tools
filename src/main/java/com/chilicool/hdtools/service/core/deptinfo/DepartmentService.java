package com.chilicool.hdtools.service.core.deptinfo;

import com.chilicool.hdtools.domain.Department;

import java.util.List;

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

    /**
     * 获取某部门分类下的部门信息
     *
     * @param deptTypeId
     * @return
     */
    public List<Department> loadAllDepartmentByDeptTypeId(Long deptTypeId);

    /**
     * 保存部门信息
     *
     * @param department
     */
    public void saveDepartment(Department department);
}
