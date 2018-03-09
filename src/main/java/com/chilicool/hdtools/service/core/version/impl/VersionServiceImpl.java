package com.chilicool.hdtools.service.core.version.impl;

import com.chilicool.hdtools.dao.VersionInfoMapper;
import com.chilicool.hdtools.domain.VersionInfo;
import com.chilicool.hdtools.service.core.version.VersionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chilicool on 2017/9/29.
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    private VersionInfoMapper versionInfoMapper;

    private long saveVersionIntoDB(VersionInfo version) {
        return versionInfoMapper.insert(version);
    }

    @Override
    public VersionInfo createVersion() {
        VersionTools versionTools = new VersionTools();
        VersionInfo version = versionTools.generateVersion();
        saveVersionIntoDB(version);
        return version;
    }

    @Override
    public VersionInfo createVersion(Long pid, String nextMajorCode) {
        VersionTools versionTools = new VersionTools();
        VersionInfo version = versionTools.generateVersion(pid, nextMajorCode);
        saveVersionIntoDB(version);
        return version;
    }

    @Deprecated
    @Override
    public VersionInfo createVersionWithPhase(String phase) {
        VersionTools versionTools = new VersionTools();
        VersionInfo version = versionTools.generateVersion(phase);
        saveVersionIntoDB(version);
        return version;
    }

    @Deprecated
    @Override
    public VersionInfo release(VersionInfo currVersion) {
        VersionTools versionTools = new VersionTools();
        versionTools.upgradeVersionOnSlave(currVersion);
        long verId = saveVersionIntoDB(currVersion);
        currVersion.setId(verId);
        return currVersion;
    }

    /**
     * 版本升级
     *
     * @param currVersion
     * @return
     */
    @Deprecated
    @Override
    public VersionInfo milestoneRelease(VersionInfo currVersion) {
        VersionTools versionTools = new VersionTools();
        versionTools.upgradeVersionOnMajor(currVersion);
        long verId = saveVersionIntoDB(currVersion);
        currVersion.setId(verId);
        return currVersion;
    }

    @Override
    public VersionInfo loadVersionWithProjId(Long verId) {
        return null;
    }

    class VersionTools {
        // 合成版本编号
        private String buildVersionInfo(VersionInfo currVersion) {
            // return currVersion.getVerCode() + currVersion.getMajor() + VersionBase.initVersionSep + currVersion.getSlave();
            return currVersion.getVerCode() + formatVersioNum(currVersion.getMajor(), false);
        }

        // 初始版本基础信息
        private void initVersionBseInfo(VersionInfo currVersion) {
            currVersion.setCreateTime(new Date());
            currVersion.setVerName(VersionBase.initVersionName);
            currVersion.setVerCode(VersionBase.initVersionCode);
            // currVersion.setSlave(VersionBase.initSlaveVersion);

        }

        // 设置版本号信息
        private void initVersionMajorInfo(VersionInfo currVersion, String nextMajorCode){
            if(StringUtils.isNotEmpty(nextMajorCode)){
                currVersion.setMajor(nextMajorCode);
            } else {
                currVersion.setMajor(VersionBase.initMajorVersion);
            }
        }

        // 设置版本信息
        private void initVersionExtInfo(VersionInfo currVersion) {
            // 设置版本信息
            String verInfo = buildVersionInfo(currVersion);
            currVersion.setVerInfo(verInfo);
            // 设置版本状态
            currVersion.setVerStatus(VersionBase.initVersionStatus);
            // 设置版本属性
            currVersion.setVerType(VersionBase.initVersionNormal);
        }

        /**
         * 版本信息与阶段无关，单独管理
         *
         * @param phase
         * @return
         */
        @Deprecated
        public VersionInfo generateVersion(String phase) {
            VersionInfo currVersion = new VersionInfo();

            currVersion.setMajor(phase);
            initVersionBseInfo(currVersion);
            initVersionExtInfo(currVersion);
            return currVersion;
        }

        public VersionInfo generateVersion() {
            VersionInfo currVersion = new VersionInfo();
            initVersionBseInfo(currVersion);
            initVersionMajorInfo(currVersion, null);
            initVersionExtInfo(currVersion);
            return currVersion;
        }

        public VersionInfo generateVersion(Long pid, String nextMajorCode) {
            VersionInfo currVersion = new VersionInfo();
            initVersionBseInfo(currVersion);
            initVersionMajorInfo(currVersion, nextMajorCode);
            initVersionExtInfo(currVersion);

            currVersion.setPid(pid);
            return currVersion;
        }

        // 升级版本
        private void upgradeVersion(VersionInfo currVersion, boolean major) {
            currVersion.setCreateTime(new Date());

            if (major) {
                String majorNum = currVersion.getMajor();
                // Integer majorNumVal = Integer.valueOf(majorNum) + 1;
                currVersion.setMajor(formatVersioNum(majorNum, true));
                // currVersion.setSlave(VersionBase.initMajorVersion);
                // currVersion.setVerType(VersionBase.initVersionMilestone);
                currVersion.setVerType(VersionBase.initVersionNormal);
            } else {
                String slave = currVersion.getSlave();
                // Integer slaveNum = Integer.valueOf(slave) + 1;
                currVersion.setSlave(formatVersioNum(slave, true));
                currVersion.setVerType(VersionBase.initVersionNormal);
            }

            currVersion.setVerInfo(buildVersionInfo(currVersion));
        }

        // 版本格式化
        private String formatVersioNum(Integer versionNumVal) {
            return versionNumVal < 10 ? "0" + versionNumVal : versionNumVal + "";
        }

        // 升级且格式化版本
        private String formatVersioNum(String versionNum, boolean upgrade) {
            Integer versionNumVal = Integer.valueOf(versionNum);
            if (upgrade) {
                versionNumVal++;
            }
            return formatVersioNum(versionNumVal);
        }

        /**
         * 重新确定版本机制，不再使用从版本号
         *
         * @param currVersion
         */
        @Deprecated
        public void upgradeVersionOnSlave(VersionInfo currVersion) {
            upgradeVersion(currVersion, false);
        }

        /**
         * 每个阶段的版本都从01开始，顺序升级，不再使用从版本号
         *
         * @param currVersion
         */
        public void upgradeVersionOnMajor(VersionInfo currVersion) {
            upgradeVersion(currVersion, true);
        }
    }

    interface VersionBase {
        String initVersionName = "项目版本"; // 版本名称
        String initVersionCode = "V";       // 版本前缀
        String initSlaveVersion = "01";     // 初始从版本编号
        String initMajorVersion = "01";      // 初始主版本编号
        String initVersionSep = ".";        // 版本分隔
        String initVersionStatus = "1";     // 使用中
        String initVersionNormal = "1";     // 普通版本
        String initVersionMilestone = "2";  // 里程碑
    }
}
