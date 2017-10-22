package com.chilicool.hdtools.service.core.sysdict;

import com.chilicool.hdtools.domain.SmSysDict;

import java.util.List;

/**
 * Created by chilicool on 2017/10/21.
 */
public interface SmSysDictService {
    public SmSysDict getSmSysDict(String typeCode, String paramCode);

    public List<SmSysDict> getSmSysDictList(String typeCode);

    public List<SmSysDict> getSmSysDictList(String typeCode, boolean notParent);

    public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode, String typeCode);

    public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode);
}
