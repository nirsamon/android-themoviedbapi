package com.nino.codilitytask3tmdb.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
val testDispatchers = Dispatchers(
    io = UnconfinedTestDispatcher(),
    main = UnconfinedTestDispatcher(),
    default = UnconfinedTestDispatcher(),
)
