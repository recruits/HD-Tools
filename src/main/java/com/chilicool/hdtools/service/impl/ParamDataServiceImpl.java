package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.domain.DataModuleEnum;
import com.chilicool.hdtools.domain.ModuleEnumParam;
import com.chilicool.hdtools.domain.ParamsViewWithBLOBs;
import com.chilicool.hdtools.model.ParamsViewWithAction;
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

    @Override
    public void addSpecRoomParam(ParamsViewWithAction paramsViewWithAction) {
        // 取出操作类型
        String action = paramsViewWithAction.getAction();

        // 创建模块枚举对象
        DataModuleEnum dataModuleEnum = roomParamService.buildDataModuleEnum(paramsViewWithAction);

        if(BusiConst.Action.ADD.equals(action)) {
            // 保存模块枚举对象
            dataModuleEnum.setId(null);
            roomParamService.saveDataModuleEnum(dataModuleEnum);
            paramsViewWithAction.setEnumId(dataModuleEnum.getId());
        } else {
            dataModuleEnum.setId(paramsViewWithAction.getEnumId());
            // 更新模块枚举对象
            roomParamService.updateDateModuleEnum(dataModuleEnum);
        }

        // 创建枚举参数列表
        List<ModuleEnumParam> moduleEnumParams = roomParamService.buildModuleEnumParams(paramsViewWithAction);

        if(BusiConst.Action.ADD.equals(action)) {
            // 保存枚举参数列表
            roomParamService.saveModuleEnumParams(moduleEnumParams);
        } else {
            // 更新枚举参数列表
            roomParamService.updateModuleEnumParams(dataModuleEnum.getId(), moduleEnumParams);
        }
    }

    @Override
    public boolean ifEnumNameExist(Long moduleId, String enumName) {
        return roomParamService.ifEnumNameExist(moduleId, enumName);
    }
}
