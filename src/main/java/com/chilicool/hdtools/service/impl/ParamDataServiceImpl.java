package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.model.SpecRoomDataJson;
import com.chilicool.hdtools.model.SpecRoomDataModel;
import com.chilicool.hdtools.service.ParamDataService;
import com.chilicool.hdtools.service.core.roomdata.RoomParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zechenzhang on 2017/11/14.
 */
@Service
public class ParamDataServiceImpl implements ParamDataService {
    @Autowired
    private RoomParamService roomParamService;

    @Override
    public SpecRoomDataJson loadAllSpecRoomData() {
        SpecRoomDataJson specRoomDataJson = new SpecRoomDataJson();
        List<SpecRoomDataModel> specRoomDataModels = roomParamService.loadAllSpecRoomData();
        specRoomDataJson.setData(specRoomDataModels);
        return specRoomDataJson;
    }
}
