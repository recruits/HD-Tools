package com.chilicool.hdtools.service;

import com.chilicool.hdtools.model.AreaInfoModel;
import com.chilicool.hdtools.model.AreaSumyModel;
import com.chilicool.hdtools.model.AreaWithAction;
import com.chilicool.hdtools.model.RoomWithAction;

import java.util.List;

/**
 * Created by chilicool on 2017/10/15.
 */
public interface ProjAreaInfoService {
    /**
     * 加载所有区域信息，包含区域和房间信息
     *
     * @param deptId
     * @return
     */
    public List<AreaInfoModel> loadAllAreaInfo(Long deptId);

    /**
     * 加载区域关联信息
     *
     * @param deptId
     * @param deptTypeId
     * @return
     */
    public AreaSumyModel loadAreaTitle(Long deptId, Long deptTypeId);

    /**
     * 编辑区域信息
     *
     * @param areaWithAction
     * @return
     */
    public void saveOrUpdateAreaInfo(AreaWithAction areaWithAction);

    /**
     * 编辑房间信息
     *
     * @param roomWithAction
     * @return
     */
    public void saveOrUpdateRoomInfo(RoomWithAction roomWithAction);
}
