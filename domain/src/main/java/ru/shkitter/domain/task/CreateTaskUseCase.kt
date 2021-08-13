package ru.shkitter.domain.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.shkitter.domain.common.usecase.FlowUseCase
import ru.shkitter.domain.task.model.CreateTaskParams

interface CreateTaskUseCase : FlowUseCase<CreateTaskParams, Unit>

class CreateTaskUseCaseImpl(
    private val taskDataSource: TaskDataSource
) : CreateTaskUseCase {

    override fun execute(param: CreateTaskParams): Flow<Result<Unit>> = flow {
        emit(Result.success(taskDataSource.createTask(param)))
    }
}