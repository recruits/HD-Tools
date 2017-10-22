package com.chilicool.hdtools.support;

import com.chilicool.hdtools.common.util.SpringContextUtil;
import com.chilicool.hdtools.domain.SmSysDict;
import com.chilicool.hdtools.service.core.sysdict.SmSysDictService;
import org.apache.commons.lang.StringUtils;

/**
 * Created by chilicool on 2017/9/19.
 */
public class TypeCodeUtil {
    public static String getCodeDesc(String typeCode, String paramCode) {
        String result = null;
        SmSysDictService smSysDictService = SpringContextUtil.getBean(SmSysDictService.class);

        typeCode = StringUtils.isNotEmpty(typeCode) ? typeCode.toUpperCase() : typeCode;
        SmSysDict bean = smSysDictService.getSmSysDict(typeCode, paramCode);
        if (null != bean) {
            result = bean.getParamDesc();
        }
        return result;
    }
}
