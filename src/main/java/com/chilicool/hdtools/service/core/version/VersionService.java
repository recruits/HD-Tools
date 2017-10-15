package com.chilicool.hdtools.service.core.version;

import com.chilicool.hdtools.domain.VersionInfo;

/**
 * <p>
 *     版本管理
 *     -- 当前阶段的实现，版本服务与项目阶段、项目信息严重耦合
 *     -- 后续出现其它需要版本控制的内容，则重构当前服务，实现版本信息与项目和项目阶段的完全解耦
 * </p>
 *
 * Created by chilicool on 2017/9/29.
 */
public interface VersionService {

    /**
     * 获取一个初始版本
     *
     * @return
     */
    public VersionInfo createVersion();

    /**
     * 为某个阶段创建一个版本信息
     *
     * @param phase
     * @return
     */
    @Deprecated
    public VersionInfo createVersionWithPhase(String phase);

    /**
     * 升级当前版本
     *
     * @param currVersion
     * @return
     */
    @Deprecated
    public VersionInfo release(VersionInfo currVersion);

    /**
     * 升级当前版本，升级到稳定版本
     *
     * @param currVersion
     * @return
     */
    public VersionInfo milestoneRelease(VersionInfo currVersion);

    /**
     * 加载项目的版本信息
     *
     * @param verId
     * @return
     */
    public VersionInfo loadVersionWithProjId(Long verId);
}
