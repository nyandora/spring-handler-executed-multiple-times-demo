package com.example.springbootdemo

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class DemoControllerAdvice() {
    @ModelAttribute("myModel")
    fun addOneObject(request: HttpServletRequest): String? {
        val cached = request.getAttribute("myModelAttr") as String?
        if (cached != null) {
            return cached
        }

        val modelValue = "model!!"
        request.setAttribute("myModelAttr", modelValue)

        return modelValue
    }

    // TODO: ExceptionHandlerの場合にどうなるか要確認。
}