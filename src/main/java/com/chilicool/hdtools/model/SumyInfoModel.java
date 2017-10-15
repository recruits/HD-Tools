package com.chilicool.hdtools.model;

import com.chilicool.hdtools.domain.DeptType;

import java.util.Date;

/**
 * Created by chilicool on 2017/10/10.
 */
public class SumyInfoModel extends DeptType{
    private Long pid;
    private Integer level;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
