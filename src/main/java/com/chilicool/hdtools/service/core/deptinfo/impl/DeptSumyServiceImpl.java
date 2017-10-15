package com.chilicool.hdtools.service.core.deptinfo.impl;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.dao.DeptSummaryMapper;
import com.chilicool.hdtools.domain.DeptSummary;
import com.chilicool.hdtools.domain.DeptSummaryExample;
import com.chilicool.hdtools.service.core.deptinfo.DeptSumyService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.chilicool.hdtools.service.core.deptinfo.impl.DeptSumyServiceImpl.DeptInitData.initArea;
import static com.chilicool.hdtools.service.core.deptinfo.impl.DeptSumyServiceImpl.DeptInitData.initPersent;

/**
 * Created by chilicool on 2017/10/14.
 */
@Service
public class DeptSumyServiceImpl implements DeptSumyService {
    @Autowired
    private DeptSummaryMapper deptSummaryMapper;

    @Override
    public void initProjDeptSumy(Long projId) {
        DeptSummary deptSummary = generateSumyInfo(projId);
        deptSummaryMapper.insert(deptSummary);
    }

    @Override
    public DeptSummary loadDeptSummaryByPK(Long projId) {
        DeptSummaryExample example = buildDeptSumyExampleWithProjId(projId);
        List<DeptSummary> deptSummaries = deptSummaryMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(deptSummaries) ? deptSummaries.get(0) : (new DeptSummary());
    }

    private DeptSummary generateSumyInfo(Long projId){
        DeptSummary deptSummary = new DeptSummary();
        // 设置初始百分比
        deptSummary.setPlanAreaPersent(initPersent);
        deptSummary.setDesignAreaPersent(initPersent);
        // 设置初始面积
        deptSummary.setPlanArea(initArea);
        deptSummary.setDesignArea(initArea);
        deptSummary.setProjId(projId);
        // 设置初始面积系统
        deptSummary.setAreaRatio(BusiConst.DobuleVal.oneVal);
        return deptSummary;
    }

    private DeptSummaryExample buildDeptSumyExampleWithProjId(Long projId) {
        DeptSummaryExample example = new DeptSummaryExample();
        example.createCriteria().andProjIdEqualTo(projId);
        return example;
    }

    interface DeptInitData{
        Double initArea = new Double(0);
        Double initPersent = new Double("100");
    }
}
