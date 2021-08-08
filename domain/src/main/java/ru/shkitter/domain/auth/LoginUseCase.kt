package ru.shkitter.domain.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.shkitter.domain.auth.model.LoginParams
import ru.shkitter.domain.common.usecase.FlowUseCase

interface LoginUseCase : FlowUseCase<LoginParams, Unit>

class LoginUseCaseImpl(
    private val authDataSource: AuthDataSource
) : LoginUseCase {

    override fun execute(param: LoginParams): Flow<Result<Unit>> = flow {
        authDataSource.login(param.email, param.password)
        emit(Result.success(Unit))
    }
}