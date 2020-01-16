package com.example.nandi.footballclub.util

import kotlinx.coroutines.experimental.Unconfined
import org.junit.Test

import org.junit.Assert.*
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProviderTest : CoroutineContextProvider() {

    override val main: CoroutineContext = Unconfined
}