package com.example.springbootdemo

import org.springframework.stereotype.Component
import org.springframework.web.context.request.async.WebAsyncUtils
import javax.servlet.http.HttpServletRequest

@Component
class AsyncHandlerUtil {

    fun isDispatchToResume(request: HttpServletRequest): Boolean {
        val manager = WebAsyncUtils.getAsyncManager(request)
        return manager.hasConcurrentResult()
   }
}