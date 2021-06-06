package com.ddwj.kimgram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //웹 설정 파일

    @Value("${file.path}")
    private String uploadFolder; // uploadFolder는 yml의 file: path:를 가리킨다.

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**")
                // JSP페이지에서 /upload/** 이런 주소 패턴이 나오면 실행한다.
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6) // 60초 * 10 = 10분 10분*6=60분(1시간)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
