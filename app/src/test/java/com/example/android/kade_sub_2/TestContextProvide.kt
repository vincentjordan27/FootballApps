package com.example.android.kade_sub_2

import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class TestContextProvide : CoroutineContextProvide() {
    @ExperimentalCoroutinesApi
    override val main: CoroutineContext = Dispatchers.Unconfined

}