package com.chilicool.hdtools.service;

import com.chilicool.hdtools.model.SpecRoomDataJson;

/**
 * Created by zechenzhang on 2017/11/14.
 */
public interface ParamDataService {
    /**
     * 加载所有房间模块数据
     *
     * @return
     */
    public SpecRoomDataJson loadAllSpecRoomData();
}
