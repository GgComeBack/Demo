package com.lg.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class PageableWebFluxConfiguration implements WebFluxConfigurer {

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        ReactivePageableHandlerMethodArgumentResolver reactivePageableHandlerMethodArgumentResolver = new ReactivePageableHandlerMethodArgumentResolver();
        reactivePageableHandlerMethodArgumentResolver.setMaxPageSize(2147483647);
        configurer.addCustomResolver(reactivePageableHandlerMethodArgumentResolver);
    }

}