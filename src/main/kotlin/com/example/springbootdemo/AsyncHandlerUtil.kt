package com.example.springbootdemo

import org.springframework.stereotype.Component
import org.springframework.web.context.request.async.WebAsyncUtils
import javax.servlet.http.HttpServletRequest

@Component
class AsyncHandlerUtil {

    fun isDispatchedToResume(request: HttpServletRequest): Boolean {
        val manager = WebAsyncUtils.getAsyncManager(request)

        // WebAsyncManager.hasConcurrentResult()を利用すれば、
        // 非同期処理（Controllerのsuspend fun）の完了後に再度ディスパッチされたのかどうかを判定できる。
        // see: https://spring.pleiades.io/spring-framework/docs/current/javadoc-api/org/springframework/web/context/request/async/WebAsyncManager.html
        return manager.hasConcurrentResult()
   }
}