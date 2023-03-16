package com.example.demo.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * ClassName: CORSConfiguration
 * Author: wangxiaokang
 * Date: 2/3/2023 4:07 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
@Configuration//spring2.0以上
public class CORSConfiguration extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
        super.addCorsMappings(registry);
    }
}
