package com.chilicool.hdtools.service.core.areainfo.impl;

import com.chilicool.hdtools.dao.RoomInfoMapper;
import com.chilicool.hdtools.domain.RoomInfo;
import com.chilicool.hdtools.domain.RoomInfoExample;
import com.chilicool.hdtools.service.core.areainfo.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zechenzhang on 2017/11/12.
 */
@Service
public class RoomInfoServiceImpl implements RoomInfoService {
    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Override
    public List<RoomInfo> loadAllRoomInfoByAreaId(Long areaId) {
        RoomInfoExample example = buildExampleByAreaId(areaId);
        return roomInfoMapper.selectByExample(example);
    }

    @Override
    public void saveRoomInfo(RoomInfo roomInfo) {
        roomInfoMapper.insert(roomInfo);
    }

    private RoomInfoExample buildExampleByAreaId(Long areaId) {
        RoomInfoExample example = new RoomInfoExample();
        example.createCriteria().andAreaIdEqualTo(areaId);
        return example;
    }
}
