package com.chilicool.hdtools.config;

import com.chilicool.hdtools.common.util.SpringContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chilicool on 2017/10/21.
 */
@Configuration
public class ServiceConfig {
    @Bean(name = "springContextUtil")
    public SpringContextUtil getSpringContextUtil(ApplicationContext applicationContext) {
        SpringContextUtil springContextUtil = new SpringContextUtil();
        springContextUtil.setApplicationContext(applicationContext);
        return springContextUtil;
    }
}
