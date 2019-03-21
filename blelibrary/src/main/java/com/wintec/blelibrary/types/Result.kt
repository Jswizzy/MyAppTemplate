package com.wintec.blelibrary.types

import java.lang.Exception

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T): Result<T>()
    data class Error(val message: String, val cause: Exception? = null): Result<Nothing>()
}

val <T> T.exhaustive: T
    get() = this