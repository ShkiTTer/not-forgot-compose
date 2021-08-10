package ru.shkitter.notforgot.presentation.task.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import org.koin.androidx.compose.getViewModel
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppIconFab
import ru.shkitter.notforgot.presentation.common.components.AppSnackbar
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import ru.shkitter.notforgot.presentation.common.components.ContentStateBox
import ru.shkitter.notforgot.presentation.common.theme.BgMain

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
                .systemBarsPadding()
        )

        AppSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            modifier = Modifier.navigationBarsPadding()
        )
    }
}

@Composable
fun TaskListContent(viewModel: TaskListViewModel) {

}