package com.chilicool.hdtools.service.core.areainfo.impl;

import com.chilicool.hdtools.dao.AreaSummaryMapper;
import com.chilicool.hdtools.domain.AreaSummary;
import com.chilicool.hdtools.domain.AreaSummaryExample;
import com.chilicool.hdtools.service.core.areainfo.AreaSumyService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zechenzhang on 2017/11/12.
 */
@Service
public class AreaSumyServiceImpl implements AreaSumyService {
    @Autowired
    private AreaSummaryMapper areaSummaryMapper;

    @Override
    public AreaSummary loadAreaSummaryByDeptId(Long deptId) {
        AreaSummaryExample example = buildExampleByDeptId(deptId);
        List<AreaSummary> areaSummaryList = areaSummaryMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(areaSummaryList) ? areaSummaryList.get(0) : null;
    }

    @Override
    public void updateAreaSummaryByPK(AreaSummary areaSummary) {
        areaSummaryMapper.updateByPrimaryKeySelective(areaSummary);
    }

    @Override
    public void saveAreaSummary(AreaSummary areaSummary) {
        areaSummaryMapper.insert(areaSummary);
    }

    private AreaSummaryExample buildExampleByDeptId(Long deptId) {
        AreaSummaryExample example = new AreaSummaryExample();
        example.createCriteria().andDeptIdEqualTo(deptId);
        return example;
    }
}
