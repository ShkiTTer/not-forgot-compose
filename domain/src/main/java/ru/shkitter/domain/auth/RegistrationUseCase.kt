package ru.shkitter.domain.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.shkitter.domain.auth.model.RegistrationParams
import ru.shkitter.domain.common.usecase.FlowUseCase

interface RegistrationUseCase : FlowUseCase<RegistrationParams, Unit>

class RegistrationUseCaseImpl(
    private val authDataSource: AuthDataSource
) : RegistrationUseCase {

    override fun execute(param: RegistrationParams): Flow<Result<Unit>> = flow {
        authDataSource.registration(
            email = param.email,
            name = param.name,
            password = param.password
        )
        emit(Result.success(Unit))
    }
}