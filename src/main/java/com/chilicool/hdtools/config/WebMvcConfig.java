package com.chilicool.hdtools.config;

import com.chilicool.hdtools.converters.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chilicool on 2017/10/7.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private StringToDateConverter stringToDateConverter;

    /**
     * 字符串转换为Date对象/客户端请求配置
     *
     * @return
     */
    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(stringToDateConverter);
        bean.setConverters(converters);
        return bean.getObject();
    }

}
