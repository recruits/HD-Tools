package com.chilicool.hdtools.service.busi.impl;

import com.chilicool.hdtools.dao.AreaSummaryMapper;
import com.chilicool.hdtools.domain.AreaSummary;
import com.chilicool.hdtools.domain.AreaSummaryExample;
import com.chilicool.hdtools.model.AreaSumyModel;
import com.chilicool.hdtools.service.busi.AreaSummaryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chilicool on 2017/10/22.
 */
@Service
public class AreaSummaryServiceImpl implements AreaSummaryService {
    @Autowired
    private AreaSummaryMapper areaSummaryMapper;

    @Override
    public AreaSummary loadAreaSummaryByDeptId(Long deptId) {
        AreaSummaryExample example = new AreaSummaryExample();
        example.createCriteria().andDeptIdEqualTo(deptId);
        List<AreaSummary> areaSummaries = areaSummaryMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(areaSummaries) ? areaSummaries.get(0) : (new AreaSumyModel());
    }

    @Override
    public void updateAreaSummaryByPK(AreaSummary areaSummary) {
        areaSummaryMapper.updateByPrimaryKeySelective(areaSummary);
    }
}
