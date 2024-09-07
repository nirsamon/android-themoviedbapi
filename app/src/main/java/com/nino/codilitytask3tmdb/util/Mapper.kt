package com.nino.codilitytask3tmdb.util

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}
