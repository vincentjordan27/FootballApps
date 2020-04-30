package com.example.android.kade_sub_2.coroutine

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvide {
    open val main: CoroutineContext by lazy {Dispatchers.Main}
}