package com.chilicool.hdtools.service;

import com.chilicool.hdtools.domain.RoomInfo;
import com.chilicool.hdtools.model.RoomParamJson;
import com.chilicool.hdtools.model.RoomSumyModel;

import java.util.List;

/**
 * Created by chilicool on 2017/10/10.
 */
public interface ProjRoomInfoService {
    /**
     * 加载全部房间参数-规格数据
     *
     * @return
     */
    public List<RoomParamJson> loadAllRoomSpecs();

    /**
     * 加载房间参数设置
     *
     * @return
     */
    public List<String> loadCurrRoomDeail(Long roomId);

    /**
     * 查询房间汇总信息
     *
     * @param areaId
     * @param roomId
     * @return
     */
    public RoomSumyModel loadCurrRoomTitle(Long areaId, Long roomId);

    /**
     * 提交房间参数变更
     *
     * @param roomId
     * @param val
     */
    public void submitRoomDataOnTime(Long roomId, String val, String action);

    /**
     * 使用样板房间参数更新当前房间参数
     *
     * @param roomId
     * @param specRoomId
     */
    public void updateRoomDataBySpecRoomId(Long roomId, Long specRoomId);

    /**
     * 查询区域所有房间信息
     *
     * @param areaId
     * @return
     */
    public List<RoomInfo> loadAllRoomInfoByAreaId(Long areaId);

    /**
     * 保存房间信息
     *
     * @param roomInfo
     */
    public void saveRoomInfo(RoomInfo roomInfo);
}
