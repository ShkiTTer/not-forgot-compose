package ru.shkitter.domain.common.extensions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

fun <T> Flow<Result<T>>.handleOn(dispatcher: CoroutineDispatcher = Dispatchers.IO): Flow<Result<T>> =
    this
        .catch { error ->
            error.printStackTrace()
            emit(Result.failure(error))
        }.flowOn(dispatcher)