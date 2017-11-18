package com.chilicool.hdtools.service.core.areainfo.impl;

import com.chilicool.hdtools.dao.AreaInfoMapper;
import com.chilicool.hdtools.domain.AreaInfo;
import com.chilicool.hdtools.domain.AreaInfoExample;
import com.chilicool.hdtools.domain.AreaSummary;
import com.chilicool.hdtools.service.core.areainfo.AreaInfoService;
import com.chilicool.hdtools.service.core.areainfo.AreaSumyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chilicool on 2017/10/29.
 */
@Service
public class AreaInfoServiceImpl implements AreaInfoService {
    @Autowired
    private AreaInfoMapper areaInfoMapper;

    @Override
    public AreaInfo loadAreaInfoByPK(Long areaId) {
        return areaInfoMapper.selectByPrimaryKey(areaId);
    }

    @Override
    public List<AreaInfo> loadAllAreaInfoByDeptId(Long deptId) {
        AreaInfoExample example = buildExampleByDeptId(deptId);
        return areaInfoMapper.selectByExample(example);
    }

    @Override
    public void saveAreaInfo(AreaInfo areaInfo) {
        areaInfoMapper.insert(areaInfo);
    }

    private AreaInfoExample buildExampleByDeptId(Long deptId) {
        AreaInfoExample example = new AreaInfoExample();
        example.createCriteria().andDeptIdEqualTo(deptId);
        return example;
    }
}
