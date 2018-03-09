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
     * <p>
     *     保存或者更新项目信息
     *     - 创建版本信息
     *     - 创建项目识别标记
     *     - 创建项目基础信息
     *     - 创建项目部门分类
     * </p>
     *
     * @param projWithAction
     * @return
     */
    public ProjBaseInfo initAndUpdateProject(ProjWithAction projWithAction);

    /**
     * <p>
     *     加载所有项目信息，同一项目只取最新版本
     * </p>
     *
     * @return
     */
    public List<ProjBaseInfo> loadAllProjInfo();

    /**
     * <p>
     *     项目发布，升级项目版本；保存项目源版本
     * </p>
     *
     * @param projId
     * @param groupId
     * @return
     */
    public ProjBaseInfo releaseProject(Long projId, Long groupId);

    /**
     * <p>
     *     项目复制，创建新的项目
     * </p>
     *
     * @param projId
     * @return
     */
    public void cloneProject(Long projId);

    // 关键字段映射
    interface ProjKeys {
        String SRC_PROJ_ID = "srcProjId";
        String PROJ_ID = "projId";
        String GROUP_ID = "groupId";
        String VER_ID = "verId";
        String VER_INFO = "verInfo";
        String SRC_DEPT_TYPE_ID = "srcDeptTypeId";
        String DEPT_TYPE_ID = "deptTypeId";
        String SRC_DEPT_ID = "srcDeptId";
        String DEPT_ID = "deptId";
        String SRC_AREA_ID = "srcAreaId";
        String AREA_ID = "areaId";
        String SRC_ROOM_ID = "srcRoomId";
        String ROOM_ID = "roomId";
        String SRC_PARAM_ID = "srcParamId";
        String PARAM_ID = "paramId";
        String CREATE_TIME = "createTime";

        String OPER_TYPE = "operType";
    }

    enum OperType{
        COPY, RELEASE;
    }

    static final String APPEND_PROJ_NAME = " - 副本";
}
