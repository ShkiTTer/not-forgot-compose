package ru.shkitter.notforgot.presentation.task.list.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
private fun CheckedTaskListTaskItem() {
    TaskListTaskItem(
        task = Task(
            0,
            "Example",
            "Description",
            true,
            Instant.now(),
            Instant.now(),
            Category(0, ""),
            Priority(0, "", "#52CC57")
        ),
        onCheckedChanged = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun UncheckedTaskListTaskItem() {
    TaskListTaskItem(
        task = Task(
            0,
            "Example",
            "Description",
            false,
            Instant.now(),
            Instant.now(),
            Category(0, ""),
            Priority(0, "", "#FFD130")
        ),
        onCheckedChanged = {},
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    )
}

@Composable
fun TaskListTaskItem(
    task: Task,
    onCheckedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundModifier = Modifier
        .background(
            color = Color.parse(task.priority.color),
            shape = MaterialTheme.shapes.medium
        )
        .padding(horizontal = 16.dp, vertical = 12.dp)

    Box(
        modifier = modifier.then(backgroundModifier)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = task.title, color = Color.White, style = MaterialTheme.typography.h4)
                Text(
                    text = task.description,
                    color = Color.White,
                    style = MaterialTheme.typography.h5
                )
            }

            val borderModifier = Modifier.border(2.dp, Color.White, MaterialTheme.shapes.small)

            Checkbox(
                checked = task.done,
                onCheckedChange = onCheckedChanged,
                modifier = if (task.done) Modifier else borderModifier,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.Transparent,
                    checkmarkColor = Color.parse(task.priority.color)
                )
            )
        }
    }
}