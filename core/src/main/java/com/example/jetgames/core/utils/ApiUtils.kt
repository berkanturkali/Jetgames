package com.example.jetgames.core.utils

import com.example.jetgames.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

inline fun <M, E, D> networkBoundResource(
    crossinline dbQuery: () -> Flow<E?>,
    crossinline apiCall: suspend () -> M,
    crossinline saveFetchResult: suspend (M) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit,
    crossinline mapFromEntity: (E) -> D,
    crossinline shouldFetch: (E?) -> Boolean,
    refresh: Boolean,
) = flow<Resource<D>> {
    val data = dbQuery().first()
    if (refresh) {
        if (shouldFetch(data)) {
            emit(Resource.Loading())
            try {
                saveFetchResult(apiCall())
                dbQuery().collect {
                    emit(Resource.Success(mapFromEntity(it!!)))
                }
            } catch (throwable: Throwable) {
                onFetchFailed.invoke(throwable)
                emit(Resource.Error(throwable.localizedMessage ?: "Something went wrong"))
            }
        } else {
            dbQuery().collect {
                emit(Resource.Success(mapFromEntity(it!!)))
            }
        }
    }
}
