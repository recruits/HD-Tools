package com.chilicool.hdtools.model;

import com.chilicool.hdtools.domain.ProjBaseInfo;
import com.chilicool.hdtools.domain.VersionInfo;

/**
 * Created by chilicool on 2017/10/1.
 */
public class ProjBaseInfoModel {
    private ProjBaseInfo projBaseInfo;
    private VersionInfo versionInfo;

    public ProjBaseInfo getProjBaseInfo() {
        return projBaseInfo;
    }

    public void setProjBaseInfo(ProjBaseInfo projBaseInfo) {
        this.projBaseInfo = projBaseInfo;
    }

    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }
}
