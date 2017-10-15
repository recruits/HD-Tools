package com.chilicool.hdtools.model;

import com.chilicool.hdtools.domain.Department;

/**
 * Created by chilicool on 2017/10/15.
 */
public class DeptWithAction extends Department{
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
