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
     * 加载区域汇总信息
     *
     * @param deptId
     * @param deptTypeId
     * @return
     */
    public AreaSumyModel loadAreaSummary(Long deptId, Long deptTypeId);

    /**
     * 加载区域关联信息
     *
     * @param deptId
     * @param deptTypeId
     * @return
     */
    @Deprecated
    public AreaSumyModel loadAreaTitle(Long deptId, Long deptTypeId);

    /**
     * 编辑区域信息
     *
     * @param areaWithAction
     * @return
     */
    public void saveOrUpdateAreaInfo(AreaWithAction areaWithAction);

    /**
     * 删除区域信息
     *
     * @param areaId
     * @return
     */
    public void delAreaInfoByAreaId(Long areaId);

    /**
     * 编辑房间信息
     *
     * @param roomWithAction
     * @return
     */
    public void saveOrUpdateRoomInfo(RoomWithAction roomWithAction);

    /**
     * 删除房间信息
     *
     * @param roomId
     * @return
     */
    public void delRoomInfoByRoomId(Long roomId);

    /**
     * 实时更新房间个数信息
     *
     * @param roomId
     * @param roomCnt
     */
    public void editRoomCntValOnTime(Long roomId, Integer roomCnt);

    /**
     * 实时更新房间个数信息
     *
     * @param roomId
     * @param roomArea
     */
    public void editRoomAreaValOnTime(Long roomId, Double roomArea);

    /**
     * 实时更新[区域汇总]规划面积系数
     *
     * @param areaSumyId
     * @param areaRatio
     */
    public void editDeptPlanAreaRatioValOnTime(Long areaSumyId, Double areaRatio);

    /**
     * 实时更新[区域汇总]设计面积系数
     *
     * @param areaSumyId
     * @param areaRatio
     */
    public void editDeptDesignAreaRatioValOnTime(Long areaSumyId, Double areaRatio);
}
