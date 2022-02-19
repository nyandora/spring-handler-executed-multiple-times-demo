package com.example.springbootdemo

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class DemoControllerAdvice() {
    @ModelAttribute("myModel")
    fun addSomeAttributeToModel(request: HttpServletRequest): String? {

        // ２回目にディスパッチされた場合は前回の結果を使う。
        // ２回目にnullを設定するなどもアリかもしれないが、
        // 前回の結果を使う方式の方が堅牢（何回呼ばれても問題は起こり得ない）。
        val cached = request.getAttribute("myModelAttr") as String?
        if (cached != null) {
            return cached
        }

        // 属性値の生成
        val attributeValue = "model!!"

        // ２回目にディスパッチされた時のために、結果をrequestに保存する。
        request.setAttribute("myModelAttr", attributeValue)

        return attributeValue
    }

    // TODO: ExceptionHandlerの場合にどうなるか要確認。
}