package com.pLg.data.source.remote

import com.pLg.data.util.DataError

fun Response.requireSuccess(onErrorBody: (String) -> String = { it }): Result<String> {
    return if (isSuccessful) {
        com.pLg.data.util.Result.Ok(body)
    } else {
        com.pLg.data.util.Result.Err(
            DataError.Network("HTTP $code: ${onErrorBody(body)}")
        )
    }
}
