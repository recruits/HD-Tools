package com.chilicool.hdtools.service;

import com.chilicool.hdtools.domain.ProjBaseInfo;
import com.chilicool.hdtools.model.ProjBaseInfoModel;
import com.chilicool.hdtools.model.ProjInfoModel;

import java.util.List;
import java.util.Map;

/**
 * Created by chilicool on 2017/10/1.
 */
public interface ProjBaseInfoService {

    /**
     * 新建项目，返回初始信息
     *
     * @return
     */
    public ProjBaseInfo initProjForAdd(String projPhase);

    /**
     * 加载所有项目信息，同一项目只取最新版本
     *
     * @return
     */
    public List<ProjBaseInfo> loadAllProjInfo();

    /**
     * 获取项目信息
     * @param projId
     * @return
     */
    public ProjBaseInfo loadProjBaseInfoById(Long projId);

    /**
     * 保存项目信息
     *
     * @param projBaseInfoModel
     * @return
     */
    public ProjBaseInfoModel saveProjInfo(ProjBaseInfoModel projBaseInfoModel);

    /**
     * 保存项目基本信息
     *
     * @param projBaseInfo
     */
    public void saveProjBaseInfo(ProjBaseInfo projBaseInfo);

    /******************************************************************************************************************/

    /**
     * 初始化项目识别信息
     *
     * @return
     */
    public Long initProjGroup();

    /**
     * 初始化项目基础信息
     *
     * @param groupId
     * @param verId
     * @return
     */
    @Deprecated
    public Long initProjBaseInfo(Long groupId, Long verId, String verInfo);

    /**
     * 使用项目基础信息新建项目
     *
     * @param projBaseInfo
     * @return
     */
    public Long initProjBaseInfoWithParams(ProjBaseInfo projBaseInfo);

    /**
     * 获取当前项目的版本编号和当前项目的最新版本编码
     *
     * @param projId
     * @return
     */
    public Map<String, Object> getProjUpgradeVersion(Long projId, Long groupId);
}
