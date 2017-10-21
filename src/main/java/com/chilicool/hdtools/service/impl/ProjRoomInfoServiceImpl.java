package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.dao.*;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.RoomParamJson;
import com.chilicool.hdtools.model.RoomSumyModel;
import com.chilicool.hdtools.service.ProjRoomInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chilicool on 2017/10/19.
 */
@Service
public class ProjRoomInfoServiceImpl implements ProjRoomInfoService {
    @Autowired
    private RoomDataModuleMapper roomDataModuleMapper;
    @Autowired
    private DataModuleEnumMapper dataModuleEnumMapper;
    @Autowired
    private ModuleEnumParamMapper moduleEnumParamMapper;
    @Autowired
    private RoomDataDetailMapper roomDataDetailMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Override
    public List<RoomParamJson> loadAllRoomSpecs() {
        return buildAllRoomSpec(1, 0L, 0L);
    }

    @Override
    public List<String> loadCurrRoomDeail(Long roomId) {
        return roomDataDetailMapper.loadCurrRoomDeail(roomId);
    }

    @Override
    public RoomSumyModel loadCurrRoomTitle(Long areaId, Long roomId) {
        Map<String, Long> inParams = new HashMap<>();
        inParams.put("areaId", areaId);
        inParams.put("roomId", roomId);
        return roomInfoMapper.loadCurrRoomTitle(inParams);
    }

    @Override
    public void submitRoomDataOnTime(Long roomId, String val) {
        if (StringUtils.isNotEmpty(val) && null != roomId && roomId != 0L) {
            RoomDataDetail roomDataDetail = buildRoomDataDetail(roomId, val);

            // 判断参数类型
            DataModuleEnum dataModuleEnum = dataModuleEnumMapper.selectByPrimaryKey(roomDataDetail.getEnumId());
            String selType = dataModuleEnum.getSelectType();
            if (StringUtils.isNotEmpty(selType) || BusiConst.SelType.CHECKBOX.equals(selType)) {
                saveCheckboxValue(roomDataDetail);
            } else {
                saveRadioValue(roomDataDetail);
            }
        }
    }

    private void saveRadioValue(RoomDataDetail roomDataDetail) {
        delAllParamsByEnumId(roomDataDetail.getRoomId(), roomDataDetail.getEnumId());
        //  radio-先清除全部参数，然后重新插入
        roomDataDetailMapper.insert(roomDataDetail);
    }

    private void saveCheckboxValue(RoomDataDetail roomDataDetail) {
        //  checkbox-先清除全部参数，然后重新插入
        // 暂未实现
    }

    private void delAllParamsByEnumId(Long roomId, Long enumId) {
        RoomDataDetailExample example = new RoomDataDetailExample();
        example.createCriteria().andRoomIdEqualTo(roomId).andEnumIdEqualTo(enumId);
        roomDataDetailMapper.deleteByExample(example);
    }

    private void delAllParamsByModuleId(Long roomId, Long moduleId) {
        RoomDataDetailExample example = new RoomDataDetailExample();
        example.createCriteria().andRoomIdEqualTo(roomId).andModuleIdEqualTo(moduleId);
        roomDataDetailMapper.deleteByExample(example);
    }

    /**
     * 创建完整的房间参数信息
     *
     * @param roomId
     * @param val
     * @return
     */
    private RoomDataDetail buildRoomDataDetail(Long roomId, String val) {
        RoomDataDetail roomDataDetail = new RoomDataDetail();
        roomDataDetail.setRoomId(roomId);
        roomDataDetail.setModuleParam(val);
        roomDataDetail.setCreateTime(new Date());

        String[] ids = val.split("_");
        if (null != ids && ids.length == 3) {
            roomDataDetail.setModuleId(Long.valueOf(ids[0]));
            roomDataDetail.setEnumId(Long.valueOf(ids[1]));
            roomDataDetail.setParamId(Long.valueOf(ids[2]));

            roomDataDetail.setModuleEnum(val.substring(0, val.lastIndexOf("_")));
        }

        return roomDataDetail;
    }

    private List<RoomParamJson> buildAllRoomSpec(int level, Long moduleId, Long enumId) {
        List<RoomParamJson> roomParamJsons = new ArrayList<>();

        if (level == 1) {
            roomParamJsons = getAllModuleParam();
            if (CollectionUtils.isNotEmpty(roomParamJsons)) {
                for (RoomParamJson roomParamJson : roomParamJsons) {
                    List<RoomParamJson> subs = buildAllRoomSpec(level + 1, roomParamJson.getCode(), 0L);
                    if (CollectionUtils.isNotEmpty(subs)) {
                        roomParamJson.setSubs(subs);
                    }
                }
            }
        } else if (level == 2) {
            roomParamJsons = getAllEnumParam(moduleId);
            if (CollectionUtils.isNotEmpty(roomParamJsons)) {
                for (RoomParamJson roomParamJson : roomParamJsons) {
                    List<RoomParamJson> subs = buildAllRoomSpec(level + 1, moduleId, roomParamJson.getCode());
                    if (CollectionUtils.isNotEmpty(subs)) {
                        roomParamJson.setSubs(subs);
                    }
                }
            }
        } else if (level == 3) {
            roomParamJsons = getAllParamSpec(moduleId, enumId);
        }

        return roomParamJsons;
    }

    private List<RoomParamJson> getAllModuleParam() {
        return roomDataModuleMapper.getAllRoomModuleInfo();
    }

    private List<RoomParamJson> getAllEnumParam(Long moduleId) {
        return dataModuleEnumMapper.getAllModuleEnumInfo(moduleId);
    }

    private List<RoomParamJson> getAllParamSpec(Long moduleId, Long enumId) {
        Map<String, Long> inParams = new HashMap<>(2);
        inParams.put("moduleId", moduleId);
        inParams.put("enumId", enumId);
        return moduleEnumParamMapper.getAllEnumParamInfo(inParams);
    }
}
