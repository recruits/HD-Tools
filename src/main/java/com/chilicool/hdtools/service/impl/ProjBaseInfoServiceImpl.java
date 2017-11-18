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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ProjBaseInfo initProjForAdd(String projPhase) {
        ProjBaseInfo projBaseInfo = new ProjBaseInfo();
        projBaseInfo.setVerInfo("V01");
        projBaseInfo.setCreateTime(new Date());
        projBaseInfo.setId(-1L);
        projBaseInfo.setProjPhase(projPhase);
        return projBaseInfo;
    }

    @Override
    public List<ProjBaseInfo> loadAllProjInfo() {
        return projBaseInfoMapper.loadAllProjInfo();
    }

    @Override
    public ProjBaseInfo loadProjBaseInfoById(Long projId) {
        return projBaseInfoMapper.selectByPrimaryKey(projId);
    }

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
        Long projId = projBaseInfo.getId();

        if (ifBaseInfoExist(projId)) {
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
    public Long initProjBaseInfo(Long groupId, Long verId, String verInfo) {
        ProjBaseInfo projBaseInfo = new ProjBaseInfo();
        projBaseInfo.setVerId(verId);
        projBaseInfo.setVerInfo(verInfo);
        projBaseInfo.setGroupId(groupId);
        projBaseInfoMapper.insert(projBaseInfo);
        return projBaseInfo.getId();
    }

    @Override
    public Long initProjBaseInfoWithParams(ProjBaseInfo projBaseInfo) {
        projBaseInfo.setId(null);
        projBaseInfoMapper.insert(projBaseInfo);
        return projBaseInfo.getId();
    }

    @Override
    public Map<String, Object> getProjUpgradeVersion(Long projId, Long groupId) {
        Map<String, Object> returnMap = new HashMap<>();

        // 获取项目当前版本编号
        ProjBaseInfo projBaseInfo = projBaseInfoMapper.selectByPrimaryKey(projId);

        // 获取项目最新版本编码
        String maxMajorCode = projBaseInfoMapper.getMaxMajorByGroupId(groupId);
        String nextMajorCode = String.valueOf(Integer.valueOf(maxMajorCode)+1);

        returnMap.put("verId", projBaseInfo.getVerId());
        returnMap.put("verCode", nextMajorCode);

        return returnMap;
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
            return null != projBaseInfo && id.equals(projBaseInfo.getId());
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
