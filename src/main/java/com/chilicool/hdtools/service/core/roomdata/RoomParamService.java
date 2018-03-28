package com.chilicool.hdtools.service.core.roomdata;

import com.chilicool.hdtools.domain.*;
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

    /**
     * 创建房间枚举对象
     *
     * @param paramsView
     * @return
     */
    public DataModuleEnum buildDataModuleEnum(ParamsView paramsView);

    /**
     * 保存房间枚举对象
     *
     * @param dataModuleEnum
     */
    public void saveDataModuleEnum(DataModuleEnum dataModuleEnum);

    /**
     * 更新房间枚举对象
     *
     * @param dataModuleEnum
     */
    public void updateDateModuleEnum(DataModuleEnum dataModuleEnum);

    /**
     * 创建房间枚举参数列表
     *
     * @param paramsView
     * @return
     */
    public List<ModuleEnumParam> buildModuleEnumParams(ParamsViewWithBLOBs paramsView);

    /**
     * 保存房间枚举参数列表
     *
     * @param moduleEnumParams
     */
    public void saveModuleEnumParams(List<ModuleEnumParam> moduleEnumParams);

    /**
     * 更新房间枚举参数列表
     *
     * @param enumId
     * @param moduleEnumParams
     */
    public void updateModuleEnumParams(Long enumId, List<ModuleEnumParam> moduleEnumParams);

    /**
     * 保存单个房间枚举参数
     *
     * @param moduleEnumParam
     */
    public void saveModuleEnumParam(ModuleEnumParam moduleEnumParam);

    /**
     * 检查枚举名称是否已存在
     *
     * @param moduleId
     * @param enumName
     * @return
     */
    public boolean ifEnumNameExist(Long moduleId, String enumName);
}
