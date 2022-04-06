package com.example.jetgames.core.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, out T>() {

    abstract val dispatcher: CoroutineDispatcher

    abstract fun execute(params: Params? = null): Flow<T>

    operator fun invoke(params: Params? = null) = execute(params).flowOn(dispatcher)
}
