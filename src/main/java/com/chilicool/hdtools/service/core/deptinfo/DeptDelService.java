package com.chilicool.hdtools.service.core.deptinfo;

/**
 * Created by chilicool on 2017/10/20.
 */
public interface DeptDelService {
    /**
     * 删除部门信息，同时删除当前部门关联的区域信息、房间信息、房间参数
     *
     * @param deptId
     */
    public void delDeptByDeptId(Long deptId);

    /**
     * 删除区域信息，同时删除当前区域关联的房间信息、房间参数
     * @param deptId
     */
    public void delAreaByDeptId(Long deptId);

    /**
     * 删除区域信息，同时删除当前区域关联的房间信息、房间参数
     * @param areaId
     */
    public void delAreaByAreaId(Long areaId);

    /**
     * 删除房间信息，同时删除房间参数
     * @param areaId
     */
    public void delRoomByAreaId(Long areaId);

    /**
     * 删除房间信息，同时删除当前房间参数
     * @param roomId
     */
    public void delRoomByRoomId(Long roomId);
}
