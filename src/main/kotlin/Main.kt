// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import java.io.File
import kotlin.concurrent.timer
import kotlin.system.exitProcess


@Composable
@Preview
fun App() {
    MaterialTheme {
        Column {
            for (j in 1..height) {
                Row {
                    for (i in 1..width) {
                        when {
                            (snake.bodyOfSnake.first() == Pair(i, j)) -> {
                                Box(
                                    modifier = Modifier.size(40.dp, 40.dp).background(Color.Black, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(modifier = Modifier. size(10.dp, 10.dp).background(Color.Green, CircleShape))
                                }
                            }

                            /*(walls.contains(Pair(i, j))) -> {
                                Box(
                                    modifier = Modifier.size(40.dp, 40.dp).background(Color.Gray)
                                )
                            }
                            */

                            (snake.bodyOfSnake.contains(Pair(i, j))) -> {
                                Box(
                                    modifier = Modifier.size(40.dp, 40.dp).background(Color.Black, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    //Box(modifier = Modifier. size(30.dp, 30.dp).background(Color.Black, CircleShape))
                                }
                            }

                            (i == apple.appleX) && (j == apple.appleY) -> {
                                Box(modifier = Modifier.size(40.dp, 40.dp).background(Color.White)) {
                                    val img = painterResource("Drawable/apple.png")
                                    Image(painter = img, "icon")
                                }
                            }

                            (snake.bodyOfSnake.first().first == apple.appleX && snake.bodyOfSnake.first().second == apple.appleY) -> {
                                snake.bodyOfSnake = snake.bodyOfSnake + snake.bodyOfSnake.map { Pair(it.first, it.second) }.last()
                                apple = Apple()
                            }

                            else -> Box(modifier = Modifier.size(40.dp, 40.dp).background(Color.White)) {}
                        }
                    }
                }
            }
        }
        if (!list.contains(snake.bodyOfSnake.first().first) || !list.contains(snake.bodyOfSnake.first().second)) {
            condition = "игра окончена"
        }
        if (snake.bodyOfSnake.size == height * width) {
            Button(onClick = { }, modifier = Modifier.fillMaxSize()) {
                Text("WIN")
            }
        }
    }
}


@ExperimentalComposeUiApi
fun main() = application {
        timer(
        daemon = true, period = speed,
    ) {
        when (move) {
            "вверх" -> { snake.up() }
            "вниз" -> { snake.down() }
            "вправо" -> { snake.right() }
            "влево" -> { snake.left() }
            "пауза" -> {  }
        }
            when (condition) {
                "игра окончена" -> {
                    exitApplication()
                    println("Игра окончена. Ваш счёт: ${snake.bodyOfSnake.size}")
                }
            }
    }
    Window(
        onKeyEvent = {
            keyEvent(it)
            false
        },
        onCloseRequest = ::exitApplication,
        title = "Змейка",
        state = rememberWindowState(
            position = WindowPosition.Aligned(
                Alignment.Center
            ),
            width = 654.dp, height = 675.dp
        )
    ) {
        App()
    }
}