package com.example.springbootdemo

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig(
        private val customHandlerInterceptor: CustomHandlerInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(customHandlerInterceptor)
                .addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
                .excludePathPatterns("/static/**") // 除外するパス(パターン)を指定する
    }
}