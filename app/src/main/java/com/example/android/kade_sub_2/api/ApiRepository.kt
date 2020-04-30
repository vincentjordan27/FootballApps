package com.example.android.kade_sub_2.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository {
    fun doRequest(url : String):Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}