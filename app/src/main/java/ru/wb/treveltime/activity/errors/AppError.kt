package ru.wb.treveltime.activity.errors

import ru.wb.treveltime.R
import java.io.IOException

sealed class AppError(val code: Int, val info: String) : RuntimeException(info) {
    companion object {
        fun from(e: Throwable): AppError = when (e) {
            is AppError -> e
            is IOException -> NetWorkException
            else -> UnknownException
        }
    }
}

class ApiException(code: Int, message: String) : AppError(code, message)

object NetWorkException : AppError(-1, (R.string.no_network).toString())
object UnknownException : AppError(-1, (R.string.unknown_exception).toString())