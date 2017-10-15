package com.chilicool.hdtools.service;

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
    public Map<String, Object> initProject(String action);
}
