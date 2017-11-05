package com.chilicool.hdtools.service;

import com.chilicool.hdtools.domain.DataModuleEnum;
import com.chilicool.hdtools.domain.DataModuleEnumParam;
import com.chilicool.hdtools.domain.RoomDataModule;
import com.chilicool.hdtools.domain.SpecRoomData;
import com.chilicool.hdtools.model.*;

import java.util.List;

/**
 * Created by chilicool on 2017/9/10.
 */
public interface RoomDataService {

    /**
     * 获取所有房间数据，包含模块数据、模块分类数据、分类参数数据
     *
     * @return
     */
    public List<RoomDataSimp> getAllRoomDataSimp();

    /**
     * 获取所有房间模块数据
     *
     * @return
     */
    public List<DataModuleSimp> getAllDataModuleSimp();

    /**
     * 获取房间模块枚举数据
     *
     * @param moduleId
     * @return
     */
    public List<ModuleEnumSimp> getModuleEnumWithModuleId(String moduleId);

    /**
     * 获取房间模块枚举数据
     *
     * @param moduleId
     * @return
     */
    public List<EnumParamSimp> getEnumParamWithModuleIdAndEnumId(String moduleId, String enumId);

    /**
     * 新增房间数据模块
     *
     * @param roomDataModule
     */
    public void addDataModule(RoomDataModule roomDataModule);

    /**
     * 检查模块名称是否存在
     *
     * @param moduleName
     * @return
     */
    public boolean checkDataMoelExistByName(String moduleName);

    /**
     * 新增房间数据模块枚举
     *
     * @param dataModuleEnum
     */
    public void addDataModuleEnum(DataModuleEnum dataModuleEnum);

    /**
     * 检查模块枚举名称是否存在
     *
     * @param enumName
     * @return
     */
    public boolean checkDataMouleEnumExistByName(Long moduleId, String enumName);

    /**
     * 新增房间数据模块枚举参数
     *
     * @param dataModuleEnumParam
     */
    public void addDataModuleEnuParam(DataModuleEnumParam dataModuleEnumParam);

    /**
     * 检查模块枚举参数名称是否存在
     *
     * @param moduleId
     * @param enumId
     * @param paramName
     * @return
     */
    public boolean checkDataMouleEnumParamExistByName(Long moduleId, Long enumId, String paramName);

    /**
     * 加载所有样板间数据
     *
     * @return
     */
    public List<SpecRoomSimple> loadAllSpecRoomDetail();

    /**
     * 编辑样板间信息
     *
     * @param specRoomWithAction
     */
    public void editSpecRoomData(SpecRoomWithAction specRoomWithAction);

    /**
     * 样板房间名称检查
     *
     * @param specRoomName
     * @param deptTypeCode
     * @return
     */
    public boolean specRoomNameCheck(String specRoomName, String deptTypeCode);

    /**
     * 加载样板房间参数信息
     *
     * @param specRoomid
     * @return
     */
    public List<String> loadCurrRoomDeail(Long specRoomid);

    /**
     * 更新样板间的参数设置
     *
     * @param specRoomId
     * @param value
     * @param action
     */
    public void submitRoomDataOnTime(Long specRoomId, String value, String action);

    /**
     * 删除样板间
     *
     * @param specRoomId
     */
    public void delSpecRoomData(Long specRoomId);
}
