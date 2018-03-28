package com.chilicool.hdtools.service;

import com.chilicool.hdtools.model.ParamsViewWithAction;
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

    /**
     * 增加房间模块和模块参数
     *
     * @param paramsView
     */
    public void addSpecRoomParam(ParamsViewWithAction paramsView);

    /**
     * 检查当前模块下是否已经存枚举名称
     *
     * @param moduleId
     * @param enumName
     * @return
     */
    public boolean ifEnumNameExist(Long moduleId, String enumName);
}
