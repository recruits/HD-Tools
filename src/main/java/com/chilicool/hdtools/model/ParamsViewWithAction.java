package com.chilicool.hdtools.model;

import com.chilicool.hdtools.domain.ParamsViewWithBLOBs;

/**
 * Created by zechenzhang on 2017/11/26.
 */
public class ParamsViewWithAction extends ParamsViewWithBLOBs {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
