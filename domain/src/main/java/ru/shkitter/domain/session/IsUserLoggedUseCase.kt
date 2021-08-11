package ru.shkitter.domain.session

import ru.shkitter.domain.common.usecase.UseCaseWithoutParam

interface IsUserLoggedUseCase : UseCaseWithoutParam<Boolean>

class IsUserLoggedUseCaseImpl(
    private val sessionDataSource: SessionDataSource
) : IsUserLoggedUseCase {

    override suspend fun execute(): Boolean = sessionDataSource.getSessionEager() != null
}