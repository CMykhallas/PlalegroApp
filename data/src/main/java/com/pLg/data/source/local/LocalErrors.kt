package com.pLg.data.source.local

import com.pLg.data.util.DataError
import com.pLg.data.util.Result

inline fun <T> safeLocal(block: () -> T): Result<T> = try {
    Result.Ok(block())
} catch (e: Exception) {
    Result.Err(DataError.Storage("Local storage error", e))
}
