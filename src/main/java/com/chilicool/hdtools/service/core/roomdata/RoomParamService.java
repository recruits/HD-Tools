package com.chilicool.hdtools.service.core.roomdata;

import com.chilicool.hdtools.domain.RoomDataDetail;
import com.chilicool.hdtools.model.SpecRoomDataModel;

import java.util.List;

/**
 * Created by zechenzhang on 2017/11/12.
 */
public interface RoomParamService {

    /**
     * 查询房间所有参数
     *
     * @param roomId
     * @return
     */
    public List<RoomDataDetail> loadAllRoomDataDetailByRoomId(Long roomId);

    /**
     * 保存房间参数
     *
     * @param roomDataDetail
     */
    public void saveRoomDataDetail(RoomDataDetail roomDataDetail);

    /**
     * 查询所有房间模块数据
     *
     * @return
     */
    public List<SpecRoomDataModel> loadAllSpecRoomData();
}
