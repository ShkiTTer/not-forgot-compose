package ru.shkitter.domain.validation

import ru.shkitter.domain.common.usecase.UseCase

interface ValidationUseCase : UseCase<ValidationRule, Boolean>

class ValidationUseCaseImpl : ValidationUseCase {
    override suspend fun execute(param: ValidationRule): Boolean = param.validate()
}