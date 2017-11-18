package com.chilicool.hdtools.service.core.roomdata.impl;

import com.chilicool.hdtools.dao.ParamsViewMapper;
import com.chilicool.hdtools.dao.RoomDataDetailMapper;
import com.chilicool.hdtools.domain.ParamsView;
import com.chilicool.hdtools.domain.ParamsViewExample;
import com.chilicool.hdtools.domain.RoomDataDetail;
import com.chilicool.hdtools.domain.RoomDataDetailExample;
import com.chilicool.hdtools.model.SpecRoomDataModel;
import com.chilicool.hdtools.service.core.roomdata.RoomParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zechenzhang on 2017/11/12.
 */
@Service
public class RoomParamServiceImpl implements RoomParamService {
    @Autowired
    private RoomDataDetailMapper roomDataDetailMapper;
    @Autowired
    private ParamsViewMapper paramsViewMapper;

    @Override
    public List<RoomDataDetail> loadAllRoomDataDetailByRoomId(Long roomId) {
        RoomDataDetailExample example = buildExampleByRoomId(roomId);
        return roomDataDetailMapper.selectByExample(example);
    }

    @Override
    public void saveRoomDataDetail(RoomDataDetail roomDataDetail) {
        roomDataDetailMapper.insert(roomDataDetail);
    }

    @Override
    public List<SpecRoomDataModel> loadAllSpecRoomData() {
        return paramsViewMapper.loadAllSpecRoomData(new ParamsViewExample());
    }

    private RoomDataDetailExample buildExampleByRoomId(Long roomId) {
        RoomDataDetailExample example = new RoomDataDetailExample();
        example.createCriteria().andRoomIdEqualTo(roomId);
        return example;
    }
}
