package com.chilicool.hdtools.service.core.deptinfo.impl;

import com.chilicool.hdtools.dao.AreaInfoMapper;
import com.chilicool.hdtools.dao.DepartmentMapper;
import com.chilicool.hdtools.dao.RoomDataDetailMapper;
import com.chilicool.hdtools.dao.RoomInfoMapper;
import com.chilicool.hdtools.domain.RoomDataDetailExample;
import com.chilicool.hdtools.service.core.deptinfo.DeptDelService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chilicool on 2017/10/20.
 */
@Service
public class DeptDelServiceImpl implements DeptDelService{
    @Autowired
    private RoomDataDetailMapper roomDataDetailMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private AreaInfoMapper areaInfoMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void delDeptByDeptId(Long deptId) {
        // 删除所有区域信息
        delAreaByDeptId(deptId);
        // 删除当前部门信息
        departmentMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public void delAreaByDeptId(Long deptId) {
        List<Long> areaIds = areaInfoMapper.loadAllAreaIdByDeptId(deptId);
        if (CollectionUtils.isNotEmpty(areaIds)) {
            for (Long areaId : areaIds) {
                delAreaByAreaId(areaId);
            }
        }
    }

    @Override
    public void delAreaByAreaId(Long areaId) {
        // 删除所有房间
        delRoomByAreaId(areaId);
        // 删除当前区域
        areaInfoMapper.deleteByPrimaryKey(areaId);
    }

    @Override
    public void delRoomByAreaId(Long areaId) {
        List<Long> roomIds = roomInfoMapper.loadAllRoomIdByAreaId(areaId);
        if (CollectionUtils.isNotEmpty(roomIds)) {
            for (Long roomId : roomIds) {
                delRoomByRoomId(roomId);
            }
        }
    }

    @Override
    public void delRoomByRoomId(Long roomId) {
        // 删除房间参数
        delRoomDataByRoomId(roomId);
        // 删除房间信息
        roomInfoMapper.deleteByPrimaryKey(roomId);
    }

    // 删除房间参数
    private void delRoomDataByRoomId(Long roomId) {
        RoomDataDetailExample example = new RoomDataDetailExample();
        example.createCriteria().andRoomIdEqualTo(roomId);
        roomDataDetailMapper.deleteByExample(example);
    }
}
