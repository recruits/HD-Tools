package com.chilicool.hdtools.model;

/**
 * Created by chilicool on 2017/9/16.
 */
public class ModuleEnumSimp {
    private Long id;
    private String enumName;
    private String enumCode;
    private Short orderIdx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    public Short getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(Short orderIdx) {
        this.orderIdx = orderIdx;
    }
}
