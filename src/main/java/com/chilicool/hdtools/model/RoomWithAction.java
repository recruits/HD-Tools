package com.chilicool.hdtools.model;

import com.chilicool.hdtools.domain.RoomInfo;

/**
 * Created by chilicool on 2017/10/15.
 */
public class RoomWithAction extends RoomInfo{
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
