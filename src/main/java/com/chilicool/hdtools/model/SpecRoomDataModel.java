package com.chilicool.hdtools.model;

import com.chilicool.hdtools.domain.ParamsViewWithBLOBs;

/**
 * Created by zechenzhang on 2017/11/14.
 */
public class SpecRoomDataModel extends ParamsViewWithBLOBs{
    private String oper;

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
