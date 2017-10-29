package com.chilicool.hdtools.service.core.areainfo.impl;

import com.chilicool.hdtools.dao.AreaInfoMapper;
import com.chilicool.hdtools.domain.AreaInfo;
import com.chilicool.hdtools.service.core.areainfo.AreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
