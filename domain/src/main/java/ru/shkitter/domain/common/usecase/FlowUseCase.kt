package ru.shkitter.domain.common.usecase

import kotlinx.coroutines.flow.Flow
import ru.shkitter.domain.common.extensions.handleOn

interface FlowUseCase<in P, out R> {
    operator fun invoke(param: P): Flow<Result<R>> = execute(param).handleOn()
    fun execute(param: P): Flow<Result<R>>
}