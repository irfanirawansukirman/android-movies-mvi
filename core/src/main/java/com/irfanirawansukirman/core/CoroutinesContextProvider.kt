package com.irfanirawansukirman.core

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutinesContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
    open val unconfined: CoroutineContext by lazy { Dispatchers.Unconfined }
}

class TestCoroutinesContextProvider : CoroutinesContextProvider() {
    override val main: CoroutineContext
        get() = Dispatchers.Unconfined

    override val io: CoroutineContext
        get() = Dispatchers.Unconfined

    override val default: CoroutineContext
        get() = Dispatchers.Unconfined

    override val unconfined: CoroutineContext
        get() = Dispatchers.Unconfined
}