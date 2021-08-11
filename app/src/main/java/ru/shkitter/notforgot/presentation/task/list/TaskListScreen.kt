package ru.shkitter.notforgot.presentation.task.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppIconFab
import ru.shkitter.notforgot.presentation.common.components.AppSnackbar
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import ru.shkitter.notforgot.presentation.common.components.ContentStateBox
import ru.shkitter.notforgot.presentation.common.theme.BgMain
import ru.shkitter.notforgot.presentation.task.list.items.TaskListCategoryItem
import ru.shkitter.notforgot.presentation.task.list.items.TaskListTaskItem

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultTaskListScreen() {
    TaskListScreen()
}

@Composable
fun TaskListScreen() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val viewModel = getViewModel<TaskListViewModel>()

    Scaffold(
        topBar = { BaseTopAppBar(title = stringResource(id = R.string.task_list_title)) },
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
        floatingActionButton = { AppIconFab(onClick = { /*TODO*/ }, icon = Icons.Filled.Add) },
        floatingActionButtonPosition = FabPosition.End
    ) { _ ->

        ContentStateBox(
            viewModel = viewModel,
            content = { TaskListContent(viewModel) },
            snackbarHostState = scaffoldState.snackbarHostState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = BgMain)
                .navigationBarsPadding()
        )

        AppSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            modifier = Modifier.navigationBarsPadding()
        )
    }
}

@Composable
fun TaskListContent(viewModel: TaskListViewModel) {
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)
    val tasksData by viewModel.tasks.observeAsState(initial = mapOf())

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { viewModel.fetchTasks(fromRefresh = true) }) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.navigationBars,
                additionalStart = 16.dp,
                additionalEnd = 16.dp,
                additionalTop = 16.dp
            )
        ) {
            tasksData.forEach { (category, tasks) ->
                item {
                    TaskListCategoryItem(
                        category = category,
                        modifier = Modifier.padding(
                            top = if (category == tasksData.keys.first()) 0.dp else 28.dp,
                            bottom = 8.dp
                        )
                    )
                }

                items(tasks) { task ->
                    TaskListTaskItem(
                        task = task,
                        onCheckedChanged = { isChecked ->
                            viewModel.onTaskDoneChanged(task.id, isChecked)
                        },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}