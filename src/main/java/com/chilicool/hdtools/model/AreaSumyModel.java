package com.chilicool.hdtools.model;

/**
 * Created by chilicool on 2017/10/15.
 */
public class AreaSumyModel {
    private Long projId;
    private String deptTypeCode;
    private String deptTypeName;
    private String deptCode;
    private String deptName;
    private Double planArea;
    private Double designArea;
    private Double areaRatio;

    public Long getProjId() {
        return projId;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Double getPlanArea() {
        return planArea;
    }

    public void setPlanArea(Double planArea) {
        this.planArea = planArea;
    }

    public Double getDesignArea() {
        return designArea;
    }

    public void setDesignArea(Double designArea) {
        this.designArea = designArea;
    }

    public Double getAreaRatio() {
        return areaRatio;
    }

    public void setAreaRatio(Double areaRatio) {
        this.areaRatio = areaRatio;
    }
}
