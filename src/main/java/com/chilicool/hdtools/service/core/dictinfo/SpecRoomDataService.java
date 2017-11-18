package com.chilicool.hdtools.service.core.dictinfo;

import com.chilicool.hdtools.domain.ParamsView;

import java.util.List;

/**
 * Created by chilicool on 2017/11/4.
 */
public interface SpecRoomDataService {
    /**
     * 使用样板房间编号加载所有样板房间参数
     *
     * @param specRoomId
     * @return
     */
    public List<String> loadAllParamsBySpecRoomId(Long specRoomId);

    /**
     * 加载所有房间模块数据
     *
     * @return
     */
    public List<ParamsView> loadAllSpecRoomData();
}
