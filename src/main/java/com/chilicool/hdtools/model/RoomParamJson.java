package com.chilicool.hdtools.model;

import java.util.List;

/**
 * Created by chilicool on 2017/10/19.
 */
public class RoomParamJson {
    private Long code;
    private String name;
    private String type;
    private Integer level;
    private List<RoomParamJson> subs;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<RoomParamJson> getSubs() {
        return subs;
    }

    public void setSubs(List<RoomParamJson> subs) {
        this.subs = subs;
    }
}
