package ru.shkitter.notforgot.presentation.task.list.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.presentation.common.extensions.parse
import java.time.Instant

@Preview
@Composable
private fun DefaultTaskListTaskItem() {
    TaskListTaskItem(
        task = Task(
            0,
            "Example",
            "Description",
            true,
            Instant.now(),
            Instant.now(),
            Category(0, ""),
            Priority(0, "", "")
        )
    )
}

@Composable
fun TaskListTaskItem(task: Task, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = Modifier
            .background(
                color = Color.parse(task.priority.color),
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

    }
}