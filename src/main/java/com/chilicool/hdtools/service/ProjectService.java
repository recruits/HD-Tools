package com.chilicool.hdtools.service;

import com.chilicool.hdtools.domain.ProjBaseInfo;
import com.chilicool.hdtools.model.ProjWithAction;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *     项目信息服务
 * </p>
 *
 * Created by chilicool on 2017/10/7.
 */
public interface ProjectService {

    /**
     * <p>
     *     新建项目
     * </p>
     *
     * @return
     */
    public void initProjForAdd(String projPhase, Map<String, Object> returnMap);

    /**
     * <p>
     *     编辑项目
     * </p>
     *
     * @return
     */
    public void loadExisProjInfo(Long projId, Map<String, Object> returnMap);

    /**
     * <p>
     *     初始化项目
     *     - 创建版本信息
     *     - 创建项目识别标记
     *     - 创建项目基础信息
     *     - 创建项目部门分类
     * </p>
     *
     * @param action add:执行初始化工作
     * @return
     */
    @Deprecated
    public Map<String, Object> initProject(String action, Long projId);

    /**
     * 保存或者更新项目信息
     *
     * @param projWithAction
     * @return
     */
    public ProjBaseInfo initAndUpdateProject(ProjWithAction projWithAction);

    /**
     * 加载所有项目信息，同一项目只取最新版本
     *
     * @return
     */
    public List<ProjBaseInfo> loadAllProjInfo();
}
