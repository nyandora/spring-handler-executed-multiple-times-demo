package com.example.springbootdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {
    // suspend funで非同期実行される場合は、DispatcherServletに２回ディスパッチされる。
    @GetMapping("suspend")
    suspend fun indexSuspend(
            demoParameter: DemoParameter
    ): String {
        println(demoParameter.parameter)

        return "suspend handler is executed."
    }

    // funで同期実行される場合は、DispatcherServletへのディスパッチは１回のみ。
    @GetMapping("normal")
    fun indexNormal(): String {
        return "normal handler is executed."
    }

    // ControllerAdviceでのエラーハンドリング 挙動確認用
    @GetMapping("suspendError")
    suspend fun indexSuspendError(): String {
        throw IllegalStateException()
    }
}