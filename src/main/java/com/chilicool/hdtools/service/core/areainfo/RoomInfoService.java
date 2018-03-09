package com.chilicool.hdtools.service.core.areainfo;

import com.chilicool.hdtools.domain.RoomInfo;

import java.util.List;

/**
 * Created by zechenzhang on 2017/11/12.
 */
public interface RoomInfoService {

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
