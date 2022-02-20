package com.example.springbootdemo

import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.lang.IllegalStateException

class DemoArgumentResolver: HandlerMethodArgumentResolver {
    override fun supportsParameter(
            parameter: MethodParameter
    ): Boolean {
        return parameter.parameterType == DemoParameter::class.java
    }

    override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer?,
            webRequest: NativeWebRequest,
            inderFactory: WebDataBinderFactory?
    ): DemoParameter {
        return DemoParameter(
                parameter = "demo-parameter-value"
        )
    }
}