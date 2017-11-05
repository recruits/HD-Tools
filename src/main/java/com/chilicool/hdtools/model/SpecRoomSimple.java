package com.chilicool.hdtools.model;

import java.util.Date;

/**
 * Created by chilicool on 2017/11/4.
 */
public class SpecRoomSimple {
    private Long id;
    private String deptTypeCode;
    private String deptTypeName;
    private String specRoomName;
    private Date createTime;
    private Date updateTime;
    private String note;
    private String oper;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptTypeCode() {
        return deptTypeCode;
    }

    public void setDeptTypeCode(String deptTypeCode) {
        this.deptTypeCode = deptTypeCode;
    }

    public String getDeptTypeName() {
        return deptTypeName;
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }

    public String getSpecRoomName() {
        return specRoomName;
    }

    public void setSpecRoomName(String specRoomName) {
        this.specRoomName = specRoomName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
