package com.chilicool.hdtools.model;

/**
 * Created by chilicool on 2017/9/16.
 */
public class EnumParamSimp {
    private Long id;
    private String enumParamName;
    private String enumParamCode;
    private Short orderIdx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnumParamName() {
        return enumParamName;
    }

    public void setEnumParamName(String enumParamName) {
        this.enumParamName = enumParamName;
    }

    public String getEnumParamCode() {
        return enumParamCode;
    }

    public void setEnumParamCode(String enumParamCode) {
        this.enumParamCode = enumParamCode;
    }

    public Short getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(Short orderIdx) {
        this.orderIdx = orderIdx;
    }
}
