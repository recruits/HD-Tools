package com.chilicool.hdtools.model;

import java.util.List;

/**
 * Created by chilicool on 2017/9/23.
 */
public class RoomDataSimp {
    private Long moduleId;
    private String moduleCode;
    private String moduleName;
    private String moduleType;
    private Long enumId;
    private String enumCode;
    private String enumName;
    private String enumIdx;
    private String enumParams;
    private String oper;

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumIdx() {
        return enumIdx;
    }

    public void setEnumIdx(String enumIdx) {
        this.enumIdx = enumIdx;
    }

    public String getEnumParams() {
        return enumParams;
    }

    public void setEnumParams(String enumParams) {
        this.enumParams = enumParams;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
