package com.chilicool.hdtools.service.impl;

import com.chilicool.hdtools.dao.ProjBaseInfoMapper;
import com.chilicool.hdtools.dao.ProjGroupMapper;
import com.chilicool.hdtools.domain.ProjBaseInfo;
import com.chilicool.hdtools.domain.ProjGroup;
import com.chilicool.hdtools.domain.ProjVersion;
import com.chilicool.hdtools.domain.VersionInfo;
import com.chilicool.hdtools.model.ProjBaseInfoModel;
import com.chilicool.hdtools.service.ProjBaseInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chilicool on 2017/10/1.
 */
@Service
public class ProjBaseInfoServiceImpl implements ProjBaseInfoService {
    @Autowired
    private ProjGroupMapper projGroupMapper;
    @Autowired
    private ProjBaseInfoMapper projBaseInfoMapper;

    @Override
    public ProjBaseInfoModel saveProjInfo(ProjBaseInfoModel projBaseInfoModel) {
        ProjBaseInfo projBaseInfo = projBaseInfoModel.getProjBaseInfo();
        ProjGroup projGroup = new ProjGroup();
        projGroup.setProjName(projBaseInfo.getProjName());
        saveProjGroupInfo(projGroup);

        projBaseInfo.setGroupId(projGroup.getId());
        saveProjBaseInfo(projBaseInfo);

        VersionInfo versionInfo = projBaseInfoModel.getVersionInfo();
        ProjVersion projVersion = buildProjVersion(projBaseInfo, versionInfo);
        // saveProjVersion(projVersion);

        return projBaseInfoModel;
    }

    @Override
    public void saveProjBaseInfo(ProjBaseInfo projBaseInfo) {
        String projId = projBaseInfo.getNote();
        if (StringUtils.isNotEmpty(projId)) {
            projBaseInfo.setId(Long.valueOf(projId));
        }

        if (ifBaseInfoExist(projBaseInfo.getId())) {
            projBaseInfoMapper.updateByPrimaryKeySelective(projBaseInfo);
        } else {
            projBaseInfoMapper.insert(projBaseInfo);
        }
    }

    @Override
    public Long initProjGroup() {
        ProjGroup projGroup = new ProjGroup();
        projGroupMapper.insert(projGroup);
        return projGroup.getId();
    }

    @Override
    public Long initProjBaseInfo(Long groupId, Long verId) {
        ProjBaseInfo projBaseInfo = new ProjBaseInfo();
        projBaseInfo.setVerId(verId);
        projBaseInfo.setGroupId(groupId);
        projBaseInfoMapper.insert(projBaseInfo);
        return projBaseInfo.getId();
    }

    /**
     * 基础信息是否存在
     *
     * @param id
     * @return
     */
    private boolean ifBaseInfoExist(Long id) {
        if (null == id || 0 == id) {
            return false;
        } else {
            ProjBaseInfo projBaseInfo = projBaseInfoMapper.selectByPrimaryKey(id);
            return null != projBaseInfo && id == projBaseInfo.getId();
        }
    }

    @Deprecated
    private void saveProjVersion(ProjVersion projVersion) {
        // projVersionMapper.insert(projVersion);
    }

    private void saveProjGroupInfo(ProjGroup projGroup) {
        projGroupMapper.insert(projGroup);
    }

    private ProjVersion buildProjVersion(ProjBaseInfo projBaseInfo, VersionInfo versionInfo) {
        ProjVersion projVersion = new ProjVersion();
        projVersion.setProjId(projBaseInfo.getId());
        projVersion.setGroupId(projBaseInfo.getGroupId());
        projVersion.setVerStatus(versionInfo.getVerStatus());
        projVersion.setVerInfo(versionInfo.getVerInfo());
        return projVersion;
    }
}
