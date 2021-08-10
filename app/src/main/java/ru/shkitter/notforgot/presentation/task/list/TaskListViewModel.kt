package ru.shkitter.notforgot.presentation.task.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.shkitter.domain.task.GetTaskListUseCase
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData
import ru.shkitter.notforgot.presentation.common.state.ContentState
import ru.shkitter.notforgot.presentation.common.state.StateViewModel

class TaskListViewModel(
    private val getTaskListUseCase: GetTaskListUseCase
) : StateViewModel<Unit>() {

    private val _tasks = MutableLiveData<Map<Category, List<Task>>>()
    val tasks = _tasks.asLiveData()

    fun fetchTasks() {
        viewModelScope.launch {
            getTaskListUseCase()
                .onStart { _contentState.value = ContentState.Loading }
                .collect { result ->
                    result
                        .onSuccess { data ->
                            _tasks.value = data.groupBy { it.category }
                            _contentState.value = ContentState.Content
                        }
                        .onFailure {}
                }
        }
    }
}