package com.chilicool.hdtools.support;

import com.chilicool.hdtools.model.ResultBase;

/**
 * Created by chilicool on 2017/9/10.
 */
public class ResultUtil {
    public static ResultBase builtResultInfo(String... args) {
        return new ResultBase(args);
    }

    public static ResultBase builtErrorResultInfo(String... args) {
        if (null == args) {
            return new ResultBase(ResultBase.RET_CODE_FAIL, ResultBase.RET_MSG_ERROR);
        } else {
            return new ResultBase(args);
        }
    }
}
