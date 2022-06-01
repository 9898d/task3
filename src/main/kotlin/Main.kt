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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlin.concurrent.timer


@Composable
@Preview
fun app() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource("Drawable/field.jpg"), "icon")
            Column {
                for (j in 1..playingField.height) {
                    Row {
                        for (i in 1..playingField.width) {
                            when {
                                (snake.bodyOfSnake.first() == Pair(i, j)) -> {
                                    Box(
                                        modifier = Modifier.size(40.dp, 40.dp).background(headColor, CircleShape).rotate(rotate),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(painter = painterResource("Drawable/head.png"), "icon")
                                    }
                                }

                                (walls.contains(Pair(i, j))) -> {
                                    Box(modifier = Modifier.size(40.dp, 40.dp)) {
                                        Image(painter = painterResource("Drawable/cactus.png"), "icon")
                                    }
                                }

                                (snake.bodyOfSnake.contains(Pair(i, j))) -> {
                                    Box(modifier = Modifier.size(40.dp, 40.dp).background(Color.Black, CircleShape))
                                }

                                (apple.coordinates == Pair(i, j)) -> {
                                    Box(modifier = Modifier.size(40.dp, 40.dp)) {
                                        Image(painter = painterResource("Drawable/apple.png"), "icon")
                                    }
                                }

                                (watermelon.coordinates == Pair(i, j)) -> {
                                    Box(modifier = Modifier.size(40.dp, 40.dp)) {
                                        Image(painter = painterResource("Drawable/watermelon.png"), "icon")
                                    }
                                }

                                (lemon.coordinates == Pair(i, j)) -> {
                                    Box(modifier = Modifier.size(40.dp, 40.dp)) {
                                        Image(painter = painterResource("Drawable/lemon.png"), "icon")
                                    }
                                }

                                (grape.coordinates == Pair(i, j)) -> {
                                    Box(modifier = Modifier.size(40.dp, 40.dp)) {
                                        Image(painter = painterResource("Drawable/grape.png"), "icon")
                                    }
                                }

                                (snake.bodyOfSnake.first() == apple.coordinates) -> {
                                    snake.bodyOfSnake =
                                        snake.bodyOfSnake + snake.bodyOfSnake.map { Pair(it.first, it.second) }.last()
                                    freeCellsForFruit = freeCells.filter { it !in snake.bodyOfSnake }
                                    apple = Fruit()
                                }

                                (snake.bodyOfSnake.first() == watermelon.coordinates) -> {
                                    snake.bodyOfSnake =
                                        snake.bodyOfSnake + snake.bodyOfSnake.map { Pair(it.first, it.second) }
                                            .last() + snake.bodyOfSnake.map { Pair(it.first, it.second) }.last()
                                    freeCellsForFruit = freeCells.filter { it !in snake.bodyOfSnake }
                                    watermelon = Fruit()
                                    condition = "норм"
                                }

                                (snake.bodyOfSnake.first() == lemon.coordinates) -> {
                                    if (snake.bodyOfSnake.size < 200) {
                                        lemon = Fruit(name = "lemon")
                                    } else {
                                        lemon = Fruit(Pair(100, 100))
                                        grape = Fruit(name = "grape")
                                    }
                                    condition = "быстро"
                                }

                                (snake.bodyOfSnake.first() == grape.coordinates) -> {
                                    grape = Fruit(name = "grape")
                                    condition = "медленно"
                                }

                                else -> Box(modifier = Modifier.size(40.dp, 40.dp))
                            }
                        }
                    }
                }
            }
        }
        if (snake.isSnakeSmash()) {
            condition = "игра окончена"
            Button(
                onClick = { restart() },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(116, 189, 37), contentColor = Color.Black)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier.weight(1f)) {}
                    Row(modifier = Modifier.weight(2f)) { Text("LOSE", fontSize = 100.sp) }
                    Row(modifier = Modifier.weight(2f)) {
                        Text(
                            "Your score: ${snake.bodyOfSnake.size}",
                            fontSize = 50.sp
                        )
                    }
                    Row(modifier = Modifier.weight(1f)) { Text("Click to try again", fontSize = 25.sp) }
                    Row(modifier = Modifier.weight(1f)) {}
                }
            }
        }
        if (snake.bodyOfSnake.size >= freeCells.size) {
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
        when (condition) {
            "норм" -> {
                headColor = Color.White
                when (move) {
                    "вверх" -> {
                        snake.up()
                    }
                    "вниз" -> {
                        snake.down()
                    }
                    "вправо" -> {
                        snake.right()
                    }
                    "влево" -> {
                        snake.left()
                    }
                    "пауза" -> {}
                }
            }
            "быстро" -> {
                headColor = Color.Yellow
                timerFast
            }
            "медленно" -> {
                headColor = Color(255, 0, 255)
                timerSlow
            }
            "игра окончена" -> {

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
        app()
    }
}