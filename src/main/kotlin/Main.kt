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


private var color by mutableStateOf(Color.White)

private val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)

private var snake = Snake()

private var lengthOfSnake = snake.length

private var bodyOfSnake = snake.bodyOfSnake

private var listOfSnake by mutableStateOf(bodyOfSnake)

val playingField = PlayingField()

val height = playingField.height

val width = playingField.width

var apple = Apple()

val walls = Walls().newWalls()

var speed: Long by mutableStateOf(250)

var condition = ""

var timer = timer(daemon = true, period = 250) {}

@Composable
@Preview
fun App() {
    var appleY = apple.appleY
    var appleX = apple.appleX
    var stop = 0
    MaterialTheme {
        Column {
            for (j in 1..height) {
                Row {
                    for (i in 1..width) {
                        when {

                            (listOfSnake.first() == Pair(i, j)) -> {
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
                            }*/

                            (listOfSnake.contains(Pair(i, j))) -> {
                                Box(
                                    modifier = Modifier.size(40.dp, 40.dp).background(Color.Black, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    //Box(modifier = Modifier. size(30.dp, 30.dp).background(Color.Black, CircleShape))
                                }
                            }

                            (i == appleX) && (j == appleY) -> {
                                Box(modifier = Modifier.size(40.dp, 40.dp).background(Color.White)) {
                                    val img = painterResource("Drawable/apple.png")
                                    Image(painter = img, "icon")
                                }
                            }

                            (listOfSnake.first().first == appleX && listOfSnake.first().second == appleY) -> {
                                listOfSnake = listOfSnake + listOfSnake.map { Pair(it.first, it.second) }.last()
                                println(listOfSnake)
                                stop++
                                apple = Apple()
                                appleX = apple.appleX
                                appleY = apple.appleY
                            }

                            (stop != 0) -> {
                                stop = 0
                            }

                            else -> Box(modifier = Modifier.size(40.dp, 40.dp).background(color)) {}
                        }
                    }
                }
            }
        }
        if (!list.contains(listOfSnake.first().first) || !list.contains(listOfSnake.first().second)) {
            condition = "игра окончена"
        }
        if (listOfSnake.size == height * width) {
            Button(onClick = { }, modifier = Modifier.fillMaxSize()) {
                Text("WIN")
            }
        }
    }
}


@ExperimentalComposeUiApi
fun main() = application {
    var move = ""
        timer(
        daemon = true, period = speed
    ) {
        when (move) {
            "вверх" -> {
                val head = listOfSnake.first()
                listOfSnake =
                    listOf(Pair(head.first, head.second - 1)) + listOfSnake.subList(0, listOfSnake.size - 1)
            }
            "вниз" -> {
                val head = listOfSnake.first()
                listOfSnake =
                    listOf(Pair(head.first, head.second + 1)) + listOfSnake.subList(0, listOfSnake.size - 1)
            }
            "вправо" -> {
                val head = listOfSnake.first()
                listOfSnake =
                    listOf(Pair(head.first + 1, head.second)) + listOfSnake.subList(0, listOfSnake.size - 1)
            }
            "влево" -> {
                val head = listOfSnake.first()
                listOfSnake =
                    listOf(Pair(head.first - 1, head.second)) + listOfSnake.subList(0, listOfSnake.size - 1)
            }
            "пауза" -> {

            }
        }
            when (condition) {
                "ускорение" -> {
                    speed = 1000
                }
                "игра окончена" -> {
                    exitApplication()
                    println("Игра окончена. Ваш счёт: ${listOfSnake.size}")
                }
            }
    }
    Window(
        onKeyEvent = {
            if (it.type == KeyEventType.KeyUp) {
                when (it.key) {
                    Key.DirectionDown -> {
                        move = "вниз"
                    }
                    Key.DirectionUp -> {
                        move = "вверх"
                    }
                    Key.DirectionRight -> {
                        move = "вправо"
                    }
                    Key.DirectionLeft -> {
                        move = "влево"
                    }
                    Key.Spacebar -> {
                        move = "пауза"
                    }
                    Key.A -> {
                        speed = 1000
                        println(speed)
                        condition = "ускорение"
                    }
                }
            }
            false
        }, onCloseRequest = ::exitApplication, title = "Змейка", state = rememberWindowState(
            position = WindowPosition.Aligned(
                Alignment.Center
            ), width = 654.dp, height = 675.dp
        )
    ) {
        App()
    }
}