package ru.shkitter.domain.common.usecase

import kotlinx.coroutines.flow.Flow
import ru.shkitter.domain.common.extensions.handleOn

interface FlowUseCaseWithoutParam<out R> {
    operator fun invoke(): Flow<Result<R>> = execute().handleOn()
    fun execute(): Flow<Result<R>>
}