package ru.shkitter.domain.session

import ru.shkitter.domain.common.usecase.UseCaseWithoutParam

interface GetCurrentTokenUseCase: UseCaseWithoutParam<String?>

class GetCurrentTokenUseCaseImpl(
    private val sessionDataSource: SessionDataSource
): GetCurrentTokenUseCase {

    override suspend fun execute(): String? {
        return sessionDataSource.getSessionEager()?.apiToken
    }
}