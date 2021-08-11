package ru.shkitter.notforgot.presentation.task.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import java.time.Instant

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultCreateTaskScreen() {
    CreateTaskScreen(
        task = Task(
            0,
            "Example",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna wirl\n" +
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna wirl",
            false,
            Instant.now(),
            Instant.now(),
            Category(0, "Учеба"),
            Priority(0, "Important", "#FFD130")
        )
    ) {}
}

@Composable
fun CreateTaskScreen(task: Task?, onBackClick: () -> Unit) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = if (task != null) R.string.create_task_edit_title else R.string.create_task_title),
                onBackClick = onBackClick
            )
        }) {

    }
}

@Composable
private fun CreateTaskContent(task: Task?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.create_task_task_title),
            color = Color.Black,
            style = MaterialTheme.typography.h4
        )

        Spacer(modifier = Modifier.height(16.dp))


    }
}
