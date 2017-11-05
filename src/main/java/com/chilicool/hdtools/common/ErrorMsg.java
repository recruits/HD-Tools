package com.chilicool.hdtools.common;

/**
 * Created by chilicool on 2017/9/12.
 */
public class ErrorMsg {
    /*****************************  SYS  *****************************/
    public static final String NOT_WEB_ENV = "当前线程未绑定请求信息，请检查是否运行在Web环境中";

    public static final String WRONG_REQUEST = "LocaleResolver未找到，请确认该请求通过DispatcherServlet分配";

    /***************************** LOGIN *****************************/
    public static final String UN_LONGIN = "用户没有登录";

    public static final String UNVALID_USER_STATUS = "用户状态不正常，请联系系统管理员！";

    public static final String UNVALID_USER_INFO = "用户名或密码不正确!";

    /***************************** BUSI *****************************/
    public static final String ERROR_MODEL_HAS_EXIST = "该类型已存在，请修改类型名称";

    public static final String ERROR_MODEL_ENUM_HAS_EXIST = "该类型参数已存在，请修改类型参数名称";

    public static final String ERROR_ENUM_PARAM_HAS_EXIST = "该参数规格已存在，请修改参数规格名称";

    public static final String ERROR_DEPT_CODE_HAS_EXIST = "该部门编码已经存在，请使用新的编码";

    public static final String ERROR_AREA_CODE_HAS_EXIST = "该区域编码已经存在，请使用新的编码";

    public static final String ERROR_SPEC_ROOM_NAME_HAS_EXIST = "该样板房间名称已经存在，请更换一个名称";
}
