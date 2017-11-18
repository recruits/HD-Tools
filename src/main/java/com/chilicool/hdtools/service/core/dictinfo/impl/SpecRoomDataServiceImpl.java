package com.chilicool.hdtools.service.core.dictinfo.impl;

import com.chilicool.hdtools.dao.ParamsViewMapper;
import com.chilicool.hdtools.dao.SpecDataDetailMapper;
import com.chilicool.hdtools.domain.ParamsView;
import com.chilicool.hdtools.domain.ParamsViewExample;
import com.chilicool.hdtools.domain.SpecRoomData;
import com.chilicool.hdtools.service.core.dictinfo.SpecRoomDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chilicool on 2017/11/4.
 */
@Service
public class SpecRoomDataServiceImpl implements SpecRoomDataService {
    @Autowired
    private SpecDataDetailMapper specDataDetailMapper;
    @Autowired
    private ParamsViewMapper paramsViewMapper;

    @Override
    public List<String> loadAllParamsBySpecRoomId(Long specRoomId) {
        return specDataDetailMapper.loadCurrRoomDeail(specRoomId);
    }

    @Override
    public List<ParamsView> loadAllSpecRoomData() {
        return paramsViewMapper.selectByExample(new ParamsViewExample());
    }
}
