package com.example.springbootdemo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Throwable): String {
        // ハンドラ（Controllerのsupend fun）で例外がスローされた場合、
        // ２回目のディスパッチでここにくる。
        return "error occurred."
    }
}