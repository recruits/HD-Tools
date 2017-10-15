package com.chilicool.hdtools.service.core.deptinfo;

import com.chilicool.hdtools.domain.DeptType;

/**
 * Created by chilicool on 2017/10/7.
 */
public interface DeptTypeService {
    /**
     * 初始化部门分类信息
     *
     * @param projId
     */
    public void initDeptTypeInfo(Long projId);

    /**
     * 获取部门分类信息
     *
     * @param deptTypeId
     * @return
     */
    public DeptType loadDeptTypeByPK(Long deptTypeId);

    /**
     * 获取项目编号
     *
     * @param deptTypeId
     * @return
     */
    public Long getProjIdWithDeptTypeId(Long deptTypeId);
}
