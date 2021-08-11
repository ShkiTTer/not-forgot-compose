package ru.shkitter.notforgot.presentation.task.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
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

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing = _isRefreshing.asLiveData()

    private val taskList = mutableListOf<Task>()

    private fun initGroupedTasks() {
        _tasks.value = taskList.groupBy { it.category }
    }

    init {
        fetchTasks()
    }

    fun fetchTasks(fromRefresh: Boolean = false) {
        viewModelScope.launch {
            getTaskListUseCase()
                .onStart {
                    if (fromRefresh) {
                        _isRefreshing.value = true
                        return@onStart
                    }

                    _contentState.value = ContentState.Loading
                }
                .onCompletion { _isRefreshing.value = false }
                .collect { result ->
                    result
                        .onSuccess { data ->
                            taskList.apply {
                                clear()
                                addAll(data)
                            }
                            initGroupedTasks()
                            _contentState.value = ContentState.Content
                        }
                        .onFailure {}
                }
        }
    }

    fun onTaskDoneChanged(taskId: Int, done: Boolean) {
        val task = taskList.find { it.id == taskId } ?: return
        val taskPosition = taskList.indexOf(task)
        taskList[taskPosition] = task.copy(done = done)
        initGroupedTasks()
    }
}