package com.chilicool.hdtools.support;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by chilicool on 2017/9/19.
 */
public class TypeCodeMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List args) throws TemplateModelException {
        if (args == null || args.size() < 2 || null == args.get(0) || StringUtils.isEmpty(args.get(0).toString()) || null == args.get(1) || StringUtils.isEmpty(args.get(1).toString())) {
            return null;
        }

        String typeCode = args.get(0).toString();
        typeCode = typeCode.toLowerCase();
        String paramCode = args.get(1).toString();
        String result = TypeCodeUtil.getCodeDesc(typeCode, paramCode);

        if (null == result) {
            result = paramCode;
            //如果静态参数表没有找到指定的参数，则尝试返回默认值
            if (args.size() >= 3 && args.get(2) != null) {
                return args.get(2).toString();
            }
        }

        return result;
    }
}
