package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.dao.*;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.*;
import com.chilicool.hdtools.service.RoomDataService;
import com.chilicool.hdtools.service.core.roomdata.RoomParamService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private SpecRoomDataMapper specRoomDataMapper;
    @Autowired
    private SpecDataDetailMapper specDataDetailMapper;
    @Autowired
    private RoomParamService roomParamService;

    @Override
    public List<RoomDataSimp> getAllRoomDataSimp() {
        return roomDataModuleMapper.getAllRoomDataSimp();
    }

    @Override
    public List<DataModuleSimp> getAllDataModuleSimp() {
        return roomDataModuleMapper.getAllDataModuleSimp();
    }

    public List<EnumParamSimp> getEnumParamWithModuleIdAndEnumId(final String moduleId, final String enumId) {
        List<EnumParamSimp> enumParamSimpList = new ArrayList<>();
        if (StringUtils.isNotEmpty(moduleId) && StringUtils.isNumeric(moduleId)
                && StringUtils.isNotEmpty(enumId) && StringUtils.isNumeric(enumId)) {
            Map<String, Object> inParam = new HashMap<String, Object>() {
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

    @Override
    public List<SpecRoomSimple> loadAllSpecRoomDetail() {
        return specRoomDataMapper.loadAllSpecRoomDetail();
    }

    @Override
    public void editSpecRoomData(SpecRoomWithAction specRoomWithAction) {
        String action = specRoomWithAction.getAction();
        SpecRoomData specRoomData = new SpecRoomData();

        BeanUtils.copyProperties(specRoomWithAction, specRoomData);
        if (action.equals(BusiConst.Action.ADD)) {
            saveOrUpdateSpecRoomData(specRoomData, true);
        } else if (action.equals(BusiConst.Action.EDIT)) {
            saveOrUpdateSpecRoomData(specRoomData, false);
        }
    }

    @Override
    public boolean specRoomNameCheck(String specRoomName, String deptTypeCode) {
        SpecRoomDataExample example = new SpecRoomDataExample();
        example.createCriteria().andDeptTypeCodeEqualTo(deptTypeCode).andSpecRoomNameEqualTo(specRoomName);
        List<SpecRoomData> specRoomDataList = specRoomDataMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(specRoomDataList) && specRoomDataList.size() > 0;
    }

    @Override
    public List<String> loadCurrRoomDeail(Long specRoomid) {
        return specDataDetailMapper.loadCurrRoomDeail(specRoomid);
    }

    @Override
    public void submitRoomDataOnTime(Long roomId, String val, String action) {
        if (StringUtils.isNotEmpty(val) && null != roomId && roomId != 0L) {
            SpecDataDetail specDataDetail = buildRoomDataDetail(roomId, val);

            // 判断参数类型
            DataModuleEnum dataModuleEnum = dataModuleEnumMapper.selectByPrimaryKey(specDataDetail.getEnumId());
            String selType = dataModuleEnum.getSelectType();
            if (StringUtils.isNotEmpty(selType) && BusiConst.SelType.CHECKBOX.equals(selType)) {
                if (StringUtils.isNotEmpty(action) && BusiConst.Action.DEL.equals(action)) {
                    delCheckboxValue(specDataDetail);
                } else {
                    saveCheckboxValue(specDataDetail);
                }
            } else {
                saveRadioValue(specDataDetail);
            }
        }
    }

    @Override
    public void delSpecRoomData(Long specRoomId) {
        // 先删除样板间参数信息
        delAllSpecDataDetailBySpecRoomId(specRoomId);
        // 再删除样板间数据
        delSpecRoomDataByPK(specRoomId);
    }

    @Override
    public List<RoomDataDetail> loadAllRoomDataDetailByRoomId(Long roomId) {
        return roomParamService.loadAllRoomDataDetailByRoomId(roomId);
    }

    @Override
    public void saveRoomDataDetail(RoomDataDetail roomDataDetail) {
        roomParamService.saveRoomDataDetail(roomDataDetail);
    }

    private void delSpecRoomDataByPK(Long specRoomId){
        specRoomDataMapper.deleteByPrimaryKey(specRoomId);
    }

    private void delAllSpecDataDetailBySpecRoomId(Long specRoomId) {
        SpecDataDetailExample example = new SpecDataDetailExample();
        example.createCriteria().andSpecRoomIdEqualTo(specRoomId);
        specDataDetailMapper.deleteByExample(example);
    }

    private void saveRadioValue(SpecDataDetail specDataDetail) {
        delAllParamsByEnumId(specDataDetail.getSpecRoomId(), specDataDetail.getEnumId());
        //  radio-先清除全部参数，然后重新插入
        saveRoomDetailInfo(specDataDetail);
    }

    private void saveCheckboxValue(SpecDataDetail specDataDetail) {
        boolean paramExist = ifParamExist(specDataDetail.getSpecRoomId(), specDataDetail.getModuleParam());
        if (!paramExist) {
            // 先判断是否已经存在，不存在再增加
            saveRoomDetailInfo(specDataDetail);
        }
    }

    private void saveRoomDetailInfo(SpecDataDetail specDataDetail) {
        specDataDetailMapper.insert(specDataDetail);
    }

    private boolean ifParamExist(Long roomId, String paramVal) {
        SpecDataDetailExample example = buildParamDetailExampleWithParamVal(roomId, paramVal);
        List<SpecDataDetail> specDataDetails = specDataDetailMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(specDataDetails) && specDataDetails.size() > 0;
    }

    private SpecDataDetail buildRoomDataDetail(Long specRoomId, String val) {
        SpecDataDetail specDataDetail = new SpecDataDetail();
        specDataDetail.setSpecRoomId(specRoomId);
        specDataDetail.setModuleParam(val);

        String[] ids = val.split("_");
        if (null != ids && ids.length == 3) {
            specDataDetail.setModuleId(Long.valueOf(ids[0]));
            specDataDetail.setEnumId(Long.valueOf(ids[1]));
            specDataDetail.setParamId(Long.valueOf(ids[2]));

            specDataDetail.setModuleEnum(val.substring(0, val.lastIndexOf("_")));
        }

        return specDataDetail;
    }

    private void delCheckboxValue(SpecDataDetail specDataDetail) {
        delAllParamsByParamValue(specDataDetail.getSpecRoomId(), specDataDetail.getModuleParam());
    }

    private void delAllParamsByEnumId(Long roomId, Long enumId) {
        SpecDataDetailExample example = new SpecDataDetailExample();
        example.createCriteria().andSpecRoomIdEqualTo(roomId).andEnumIdEqualTo(enumId);
        specDataDetailMapper.deleteByExample(example);
    }

    private void delAllParamsByParamValue(Long roomId, String paramVal) {
        SpecDataDetailExample example = buildParamDetailExampleWithParamVal(roomId, paramVal);
        specDataDetailMapper.deleteByExample(example);
    }

    private SpecDataDetailExample buildParamDetailExampleWithParamVal(Long roomId, String paramVal) {
        SpecDataDetailExample example = new SpecDataDetailExample();
        example.createCriteria().andSpecRoomIdEqualTo(roomId).andModuleParamEqualTo(paramVal);
        return example;
    }

    private void saveOrUpdateSpecRoomData(SpecRoomData specRoomData, boolean saveFlag) {
        specRoomData.setUpdateTime(new Date());

        if (saveFlag) {
            specRoomData.setCreateTime(specRoomData.getUpdateTime());

            specRoomDataMapper.insert(specRoomData);
        } else {
            specRoomDataMapper.updateByPrimaryKeySelective(specRoomData);
        }
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
