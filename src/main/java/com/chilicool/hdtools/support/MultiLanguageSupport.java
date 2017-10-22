package com.chilicool.hdtools.support;

import com.chilicool.hdtools.common.ErrorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by chilicool on 2017/9/19.
 */
public class MultiLanguageSupport {
    private static Logger LOG = LoggerFactory.getLogger(MultiLanguageSupport.class);

    private static final String TYPE_PARAM_CODE = "VIEWS.EXCEPTION";

    public static String getLanguageType() {
        String languageType = Locale.CHINA.toString();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver != null) {
                Locale local = localeResolver.resolveLocale(request);
                languageType = local.toString();
            }
        } catch (Exception e) {
            LOG.debug("无法从请求中获取当前语言类型" + e);
        }
        return languageType;
    }

    /**
     * 设置语言区域类型
     *
     * @param locale
     * @param response
     * @param request
     * @author caohui
     * @date Aug 11, 2014 1:38:42 PM
     */
    public static void setLanguageType(String locale, HttpServletResponse response, HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new RuntimeException(ErrorMsg.WRONG_REQUEST);
        }

        LocaleEditor localeEditor = new LocaleEditor();
        localeEditor.setAsText(locale);
        localeResolver.setLocale(request, response, (Locale) localeEditor.getValue());
    }
}
