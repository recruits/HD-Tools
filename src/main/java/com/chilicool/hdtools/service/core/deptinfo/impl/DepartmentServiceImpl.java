package com.chilicool.hdtools.service.core.deptinfo.impl;

import com.chilicool.hdtools.dao.DepartmentMapper;
import com.chilicool.hdtools.domain.Department;
import com.chilicool.hdtools.domain.DepartmentExample;
import com.chilicool.hdtools.service.core.deptinfo.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chilicool on 2017/10/15.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department loadDepartmentByPK(Long deptId) {
        return departmentMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public List<Department> loadAllDepartmentByDeptTypeId(Long deptTypeId) {
        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andDeptTypeIdEqualTo(deptTypeId);
        return departmentMapper.selectByExample(example);
    }

    @Override
    public void saveDepartment(Department department) {
        departmentMapper.insert(department);
    }
}
