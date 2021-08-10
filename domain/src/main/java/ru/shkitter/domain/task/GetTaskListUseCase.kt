package ru.shkitter.domain.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.shkitter.domain.common.usecase.FlowUseCaseWithoutParam
import ru.shkitter.domain.task.model.Task

interface GetTaskListUseCase : FlowUseCaseWithoutParam<List<Task>>

class GetTaskListUseCaseImpl(
    private val taskDataSource: TaskDataSource
) : GetTaskListUseCase {

    override fun execute(): Flow<Result<List<Task>>> = flow {
        emit(Result.success(taskDataSource.getTaskList()))
    }
}