package com.example.springbootdemo

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomHandlerInterceptor(
        private val asyncHandlerUtil: AsyncHandlerUtil
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // 非同期処理（Controllerのsuspend fun）の完了後、
        // 後続処理を再開するためにDispatcherServletに再度ディスパッチされる。
        // その時にInterceptorが呼ばれた場合（つまり２回目に呼ばれた場合）は何もしない。
        if (asyncHandlerUtil.isDispatchToResume(request)) {
            // falseを返してしまうと後続処理の再開まで止まってしまうので、trueを返す必要あり。
            return true
        }

        // 非同期処理（Controllerのsuspend fun）を起動するために
        // DispatcherServletにディスパッチされた時のみ（つまり１回目に呼ばれた場合のみ）、
        // 本来の処理を行う。
        println("Interceptorが本来やるべき処理")

        return true
    }
}