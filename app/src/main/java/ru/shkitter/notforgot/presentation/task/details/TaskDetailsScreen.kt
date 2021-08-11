package ru.shkitter.notforgot.presentation.task.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import ru.shkitter.notforgot.presentation.common.extensions.parse
import ru.shkitter.notforgot.presentation.common.theme.*
import ru.shkitter.notforgot.presentation.common.utils.DateFormattingUtils
import java.time.Instant

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultTaskDetailsScreen() {
    MaterialTheme {
        TaskDetailsScreen(
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
            ),
            onBackClick = {}
        )
    }
}

@Composable
fun TaskDetailsScreen(task: Task, onBackClick: () -> Unit) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = R.string.task_details_title),
                onBackClick = { onBackClick.invoke() })
        }) { _ ->

        TaskDetailsContent(task = task)
    }
}

@Composable
fun TaskDetailsContent(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = task.title, style = MaterialTheme.typography.h2, color = Color.Black)

            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null,
                tint = AccentBlueColor
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = task.category.name,
                color = TextShadeColor,
                style = MaterialTheme.typography.button
            )

            Text(
                text = stringResource(id = if (task.done) R.string.task_details_done else R.string.task_details_undone),
                color = if (task.done) SuccessColor else ErrorColor,
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = task.description,
            color = TextBlackColor,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.WatchLater,
                    contentDescription = null,
                    tint = TextShadeColor
                )
                Text(
                    text = stringResource(
                        id = R.string.task_details_deadline,
                        DateFormattingUtils.getFullDigitDate(task.deadline)
                    ),
                    color = TextShadeColor,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.bg_priority),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.parse(task.priority.color))
                )
                Text(
                    text = task.priority.name,
                    color = Color.White,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}