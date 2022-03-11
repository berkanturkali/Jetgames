package com.example.jetgames.core.utils

import com.example.jetgames.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


suspend fun <M, T> safeApiCall(
    apiCall: suspend () -> M,
    mapFromModel: (M) -> T,
): Resource<T> {
    return try {
        val response = apiCall.invoke()
        response?.let {
            Resource.Success(mapFromModel.invoke(it as M))
        } ?: Resource.Error("No data was found")
    } catch (e: Exception) {
        return when (e) {
            is HttpException, is IOException -> {
                Resource.Error(e.localizedMessage!!)
            }
            else -> {
                Resource.Error("Something went wrong.")
            }
        }
    }
}

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
                    dbQuery().collect { emit(Resource.Success(mapFromEntity(it!!))) }

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