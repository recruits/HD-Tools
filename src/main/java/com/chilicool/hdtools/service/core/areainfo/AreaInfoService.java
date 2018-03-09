package com.chilicool.hdtools.service.core.areainfo;

import com.chilicool.hdtools.domain.AreaInfo;
import com.chilicool.hdtools.domain.AreaSummary;

import java.util.List;

/**
 * Created by chilicool on 2017/10/29.
 */
public interface AreaInfoService {

    /**
     * 查询区域信息
     *
     * @param areaId
     * @return
     */
    public AreaInfo loadAreaInfoByPK(Long areaId);

    /**
     * 查询部门的所有区域信息
     *
     * @param deptId
     * @return
     */
    public List<AreaInfo> loadAllAreaInfoByDeptId(Long deptId);

    /**
     * 保存区域信息
     *
     * @param areaInfo
     */
    public void saveAreaInfo(AreaInfo areaInfo);

}
