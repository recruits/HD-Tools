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
    private Integer planCnt;
    private Double planAreaTotal;
    private Double planAreaSummary;
    private Integer designCnt;
    private Double designAreaTotal;
    private Double designAreaSummary;
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

    public Integer getPlanCnt() {
        return planCnt;
    }

    public void setPlanCnt(Integer planCnt) {
        this.planCnt = planCnt;
    }

    public Double getPlanAreaTotal() {
        return planAreaTotal;
    }

    public void setPlanAreaTotal(Double planAreaTotal) {
        this.planAreaTotal = planAreaTotal;
    }

    public Double getPlanAreaSummary() {
        return planAreaSummary;
    }

    public void setPlanAreaSummary(Double planAreaSummary) {
        this.planAreaSummary = planAreaSummary;
    }

    public Integer getDesignCnt() {
        return designCnt;
    }

    public void setDesignCnt(Integer designCnt) {
        this.designCnt = designCnt;
    }

    public Double getDesignAreaTotal() {
        return designAreaTotal;
    }

    public void setDesignAreaTotal(Double designAreaTotal) {
        this.designAreaTotal = designAreaTotal;
    }

    public Double getDesignAreaSummary() {
        return designAreaSummary;
    }

    public void setDesignAreaSummary(Double designAreaSummary) {
        this.designAreaSummary = designAreaSummary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
