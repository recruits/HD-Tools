package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.dao.DataModuleEnumMapper;
import com.chilicool.hdtools.dao.DataModuleEnumParamMapper;
import com.chilicool.hdtools.dao.RoomDataModuleMapper;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.DataModuleSimp;
import com.chilicool.hdtools.model.EnumParamSimp;
import com.chilicool.hdtools.model.ModuleEnumSimp;
import com.chilicool.hdtools.model.RoomDataSimp;
import com.chilicool.hdtools.service.RoomDataService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chilicool.hdtools.common.ErrorMsg.*;

/**
 * Created by chilicool on 2017/9/10.
 */
@Service
public class RoomDataServiceImpl implements RoomDataService {
    @Autowired
    private RoomDataModuleMapper roomDataModuleMapper;
    @Autowired
    private DataModuleEnumMapper dataModuleEnumMapper;
    @Autowired
    private DataModuleEnumParamMapper dataModuleEnumParamMapper;

    @Override
    public List<RoomDataSimp> getAllRoomDataSimp() {
        return roomDataModuleMapper.getAllRoomDataSimp();
    }

    @Override
    public List<DataModuleSimp> getAllDataModuleSimp() {
        return roomDataModuleMapper.getAllDataModuleSimp();
    }

    public List<EnumParamSimp> getEnumParamWithModuleIdAndEnumId(String moduleId, String enumId) {
        List<EnumParamSimp> enumParamSimpList = new ArrayList<>();
        if (StringUtils.isNotEmpty(moduleId) && StringUtils.isNumeric(moduleId)
                && StringUtils.isNotEmpty(enumId) && StringUtils.isNumeric(enumId)) {
            Map<String, Object> inParam = new HashMap<String, Object>(){
                {
                    put("moduleId", Long.valueOf(moduleId));
                    put("enumId", Long.valueOf(enumId));
                }
            };
            enumParamSimpList = dataModuleEnumParamMapper.getEnumParamWithModuleAndEnumId(inParam);
        }
        return enumParamSimpList;
    }

    @Override
    public List<ModuleEnumSimp> getModuleEnumWithModuleId(String moduleId) {
        List<ModuleEnumSimp> moduleEnumSimpList = new ArrayList<>();
        if (StringUtils.isNotEmpty(moduleId) && StringUtils.isNumeric(moduleId)) {
            moduleEnumSimpList = dataModuleEnumMapper.getModuleEnumWithModuleId(Long.valueOf(moduleId));
        }
        return moduleEnumSimpList;
    }

    @Override
    public void addDataModule(RoomDataModule roomDataModule) {
        if (checkDataMoelExistByName(roomDataModule.getModuleName())) {
            throw new RuntimeException(ERROR_MODEL_HAS_EXIST);
        }

        roomDataModuleMapper.insert(roomDataModule);
    }

    @Override
    public boolean checkDataMoelExistByName(String moduleName) {
        if (StringUtils.isEmpty(moduleName)) {
            return false;
        }

        RoomDataModuleExample example = buildRoomDataModelExampleByName(moduleName);
        return CollectionUtils.isNotEmpty(roomDataModuleMapper.selectByExample(example));
    }

    @Override
    public void addDataModuleEnum(DataModuleEnum dataModuleEnum) {
        if (checkDataMouleEnumExistByName(dataModuleEnum.getModuleId(), dataModuleEnum.getEnumName())) {
            throw new RuntimeException(ERROR_MODEL_ENUM_HAS_EXIST);
        }

        dataModuleEnumMapper.insert(dataModuleEnum);
    }

    @Override
    public boolean checkDataMouleEnumExistByName(Long moduleId, String enumName) {
        if (StringUtils.isEmpty(enumName)) {
            return false;
        }

        DataModuleEnumExample example = buildDataModelEnumExampleByName(moduleId, enumName);
        return CollectionUtils.isNotEmpty(dataModuleEnumMapper.selectByExample(example));

    }

    @Override
    public void addDataModuleEnuParam(DataModuleEnumParam dataModuleEnumParam) {
        if (checkDataMouleEnumParamExistByName(dataModuleEnumParam.getModuleId(), dataModuleEnumParam.getEnumId(), dataModuleEnumParam.getEnumParamName())) {
            throw new RuntimeException(ERROR_ENUM_PARAM_HAS_EXIST);
        }

        dataModuleEnumParamMapper.insert(dataModuleEnumParam);
    }

    @Override
    public boolean checkDataMouleEnumParamExistByName(Long moduleId, Long enumId, String paramName) {
        if (StringUtils.isEmpty(paramName)) {
            return false;
        }

        DataModuleEnumParamExample example = buildDataModelEnumParamExampleByName(moduleId, enumId, paramName);
        return CollectionUtils.isNotEmpty(dataModuleEnumParamMapper.selectByExample(example));
    }

    private RoomDataModuleExample buildRoomDataModelExampleByName(String moduleName) {
        RoomDataModuleExample example = new RoomDataModuleExample();
        example.createCriteria().andModuleNameEqualTo(moduleName);
        return example;
    }

    private DataModuleEnumExample buildDataModelEnumExampleByName(Long moduleId, String enumName) {
        DataModuleEnumExample example = new DataModuleEnumExample();
        example.createCriteria().andModuleIdEqualTo(moduleId).andEnumNameEqualTo(enumName);
        return example;
    }

    private DataModuleEnumParamExample buildDataModelEnumParamExampleByName(Long moduleId, Long enumId, String paramName) {
        DataModuleEnumParamExample example = new DataModuleEnumParamExample();
        example.createCriteria().andModuleIdEqualTo(moduleId).andEnumIdEqualTo(enumId).andEnumParamNameEqualTo(paramName);
        return example;
    }
}
