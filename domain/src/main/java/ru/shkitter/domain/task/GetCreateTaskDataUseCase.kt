package ru.shkitter.domain.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.shkitter.domain.common.usecase.FlowUseCaseWithoutParam
import ru.shkitter.domain.task.model.CreateTaskData

interface GetCreateTaskDataUseCase : FlowUseCaseWithoutParam<CreateTaskData>

class GetCreateTaskDataUseCaseImpl(
    private val taskDataSource: TaskDataSource
) : GetCreateTaskDataUseCase {

    override fun execute(): Flow<Result<CreateTaskData>> = flow {
        val categories = taskDataSource.getCategories()
        val priorities = taskDataSource.getPriorities()

        emit(Result.success(CreateTaskData(categories, priorities)))
    }
}