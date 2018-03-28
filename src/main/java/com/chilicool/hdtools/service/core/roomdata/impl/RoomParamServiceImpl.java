package com.chilicool.hdtools.service.core.roomdata.impl;

import com.chilicool.hdtools.dao.DataModuleEnumMapper;
import com.chilicool.hdtools.dao.ModuleEnumParamMapper;
import com.chilicool.hdtools.dao.ParamsViewMapper;
import com.chilicool.hdtools.dao.RoomDataDetailMapper;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.SpecRoomDataModel;
import com.chilicool.hdtools.service.core.roomdata.RoomParamService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zechenzhang on 2017/11/12.
 */
@Service
public class RoomParamServiceImpl implements RoomParamService {
    @Autowired
    private RoomDataDetailMapper roomDataDetailMapper;
    @Autowired
    private ParamsViewMapper paramsViewMapper;
    @Autowired
    private DataModuleEnumMapper dataModuleEnumMapper;
    @Autowired
    private ModuleEnumParamMapper moduleEnumParamMapper;

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

    @Override
    public DataModuleEnum buildDataModuleEnum(ParamsView paramsView) {
        DataModuleEnum dataModuleEnum = new DataModuleEnum();
        dataModuleEnum.setCreateTime(new Date());
        dataModuleEnum.setEnumName(paramsView.getEnumName());
        dataModuleEnum.setModuleId(paramsView.getModuleId());
        dataModuleEnum.setSelectType(paramsView.getSelType());
        dataModuleEnum.setOrderIdx(paramsView.getEnumIdx());
        return dataModuleEnum;
    }

    @Override
    public void saveDataModuleEnum(DataModuleEnum dataModuleEnum) {
        dataModuleEnumMapper.insert(dataModuleEnum);
    }

    @Override
    public void updateDateModuleEnum(DataModuleEnum dataModuleEnum) {
        dataModuleEnumMapper.updateByPrimaryKeySelective(dataModuleEnum);
    }

    @Override
    public List<ModuleEnumParam> buildModuleEnumParams(ParamsViewWithBLOBs paramsView) {
        List<ModuleEnumParam> moduleEnumParams = new ArrayList<ModuleEnumParam>();
        String paramNames = paramsView.getParamNames();

        if (StringUtils.isNotEmpty(paramNames)) {
            Long moduleId = paramsView.getModuleId();
            Long enumId = paramsView.getEnumId();
            Date createTime = new Date();

            String[] paramNameArry = paramNames.split(",");
            for (String paramInfo : paramNameArry) {
                String[] paramVals = paramInfo.split("-");
                if (paramVals.length == 2) {
                    ModuleEnumParam moduleEnumParam = new ModuleEnumParam();
                    moduleEnumParam.setModuleId(moduleId);
                    moduleEnumParam.setEnumId(enumId);
                    moduleEnumParam.setCreateTime(createTime);
                    moduleEnumParam.setEnumParamName(paramVals[1]);
                    moduleEnumParam.setOrderIdx(Short.valueOf(paramVals[0]));

                    moduleEnumParams.add(moduleEnumParam);
                }
            }
        }
        return moduleEnumParams;
    }

    @Override
    public void saveModuleEnumParams(List<ModuleEnumParam> moduleEnumParams) {
        if (CollectionUtils.isNotEmpty(moduleEnumParams)) {
            for (ModuleEnumParam moduleEnumParam : moduleEnumParams) {
                saveModuleEnumParam(moduleEnumParam);
            }
        }
    }

    @Override
    public void updateModuleEnumParams(Long enumId, List<ModuleEnumParam> moduleEnumParams) {
        ModuleEnumParamExample example = buildExampleByEnumId(enumId);
        // 取出已存在参数列表
        List<ModuleEnumParam> moduleEnumParamList = moduleEnumParamMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(moduleEnumParamList)){
            saveModuleEnumParams(moduleEnumParams);
        } else {
            if(CollectionUtils.isNotEmpty(moduleEnumParams)) {
                Map<String, ModuleEnumParam> existParams = buildExistParams(moduleEnumParamList);

                for(ModuleEnumParam newModuleEnumParam: moduleEnumParams) {
                    // 对于已存在参数，修改顺序
                    String enumParamName = newModuleEnumParam.getEnumParamName();
                    ModuleEnumParam existParam = existParams.get(enumParamName);

                    if(null != existParam){
                        existParam.setOrderIdx(newModuleEnumParam.getOrderIdx());

                        updateModuleEnumParam(existParam);

                        existParams.remove(enumParamName);
                    } else {
                        // 对于不存在参数，直接保存
                        saveModuleEnumParam(newModuleEnumParam);
                    }
                }

                // 默认删除的内容处理
                Collection<ModuleEnumParam> delModuleEnumParams = existParams.values();
                if(CollectionUtils.isNotEmpty(delModuleEnumParams)){
                    delModuleEnumParams(delModuleEnumParams);
                }
            }
        }
    }

    private void delModuleEnumParams(Collection<ModuleEnumParam> delModuleEnumParams) {
        for (ModuleEnumParam moduleEnumParam : delModuleEnumParams) {
            moduleEnumParamMapper.deleteByPrimaryKey(moduleEnumParam.getId());
        }
    }

    private ModuleEnumParam loadEnumParamByEnumIdAndParamName(Long enumId, String enumParamName) {
        ModuleEnumParamExample example = new ModuleEnumParamExample();
        example.createCriteria().andEnumIdEqualTo(enumId).andEnumParamNameEqualTo(enumParamName);

        List<ModuleEnumParam> moduleEnumParams = moduleEnumParamMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(moduleEnumParams) ? moduleEnumParams.get(0) : null;
    }

    private Map<String, ModuleEnumParam> buildExistParams(List<ModuleEnumParam> moduleEnumParamList){
        Map<String, ModuleEnumParam> existParams = new HashMap<>();
        for(ModuleEnumParam moduleEnumParam: moduleEnumParamList){
            existParams.put(moduleEnumParam.getEnumParamName(), moduleEnumParam);
        }
        return existParams;
    }

    @Override
    public void saveModuleEnumParam(ModuleEnumParam moduleEnumParam) {
        moduleEnumParamMapper.insert(moduleEnumParam);
    }

    public void updateModuleEnumParam(ModuleEnumParam moduleEnumParam){
        moduleEnumParamMapper.updateByPrimaryKeySelective(moduleEnumParam);
    }

    @Override
    public boolean ifEnumNameExist(Long moduleId, String enumName) {
        DataModuleEnumExample enumExample = new DataModuleEnumExample();
        enumExample.createCriteria().andModuleIdEqualTo(moduleId).andEnumNameEqualTo(enumName);
        List<DataModuleEnum> dataModuleEnums = dataModuleEnumMapper.selectByExample(enumExample);
        return CollectionUtils.isNotEmpty(dataModuleEnums);
    }

    private RoomDataDetailExample buildExampleByRoomId(Long roomId) {
        RoomDataDetailExample example = new RoomDataDetailExample();
        example.createCriteria().andRoomIdEqualTo(roomId);
        return example;
    }

    private ModuleEnumParamExample buildExampleByEnumId(Long enumId){
        ModuleEnumParamExample example = new ModuleEnumParamExample();
        example.createCriteria().andEnumIdEqualTo(enumId);
        return example;
    }
}
