package com.chilicool.hdtools.service;

import com.chilicool.hdtools.domain.ProjBaseInfo;
import com.chilicool.hdtools.domain.ProjGroup;
import com.chilicool.hdtools.model.ProjBaseInfoModel;

/**
 * Created by chilicool on 2017/10/1.
 */
public interface ProjBaseInfoService {

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
    public Long initProjBaseInfo(Long groupId, Long verId);
}
