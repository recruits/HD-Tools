package com.chilicool.hdtools.model;

/**
 * Created by chilicool on 2017/10/15.
 */
public class AreaInfoModel {
    private Long id;
    private Long pid;
    private Integer level;
    private String areaName;
    private String areaCode;
    private Integer cnt;
    private Double areaTotal;
    private Double areaSummary;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(Double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public Double getAreaSummary() {
        return areaSummary;
    }

    public void setAreaSummary(Double areaSummary) {
        this.areaSummary = areaSummary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
