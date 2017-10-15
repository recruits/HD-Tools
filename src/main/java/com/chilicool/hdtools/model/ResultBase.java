package com.chilicool.hdtools.model;

import java.text.MessageFormat;

/**
 * Created by chilicool on 2017/9/10.
 */
public class ResultBase {
    public static final String RET_CODE_SUCC = "0000";
    public static final String RET_MSG_SUCC = "操作成功!";
    public static final String RET_CODE_FAIL = "1001";
    public static final String RET_MSG_ERROR = "操作失败!";
    public static final String RET_MSG_ERROR_DEF_PARAM = "{0}!";
    public static final String RET_MSG_ERROR_WITH_PARAM = "操作失败:  {0}!";

    /**
     * 构造器仅提供返回信息的包装，不包含扩展信息；扩展信息需要手工设置
     *
     * @param args
     */
    public ResultBase(String... args) {
        if (null != args) {
            if (args.length == 3) {
                this.retCode = args[0];
                this.retMsg = MessageFormat.format(args[1], args[2]);
            } else if (args.length == 2) {
                this.retCode = args[0];
                this.retMsg = args[1];
            } else if (args.length == 1) {
                this.retCode = args[0];
            } else {
                this.retCode = RET_CODE_SUCC;
                this.retMsg = RET_MSG_SUCC;
            }
        } else {
            this.retCode = RET_CODE_SUCC;
            this.retMsg = RET_MSG_SUCC;
        }
    }

    private String retCode;
    private String retMsg;
    private String retExtInfo;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetExtInfo() {
        return retExtInfo;
    }

    public void setRetExtInfo(String retExtInfo) {
        this.retExtInfo = retExtInfo;
    }
}
