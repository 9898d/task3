import androidx.compose.ui.graphics.Color
import kotlin.concurrent.timer

val playingField = PlayingField()

var snake = Snake()

var freeCells = playingField.listOfCells

var freeCellsForFruit: List<Pair<Int, Int>> = freeCells

val range = 1..playingField.height

var walls = Walls().newWalls()

var apple = Fruit()

var lemon = Fruit(name = "lemon")

var grape = Fruit(Pair(100, 100))

var watermelon = Fruit(name = "watermelon")

var speed: Long = 250

var condition = "норм"

var move = ""

var headColor = Color.White

var rotate = 90f

val timerFast = timer(
    daemon = true, period = speed / 2
) {
    when (condition) {
        "быстро" -> {
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
    }
}

val timerSlow = timer(
    daemon = true, period = speed * 2
) {
    when (condition) {
        "медленно" -> {
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
    }
}

fun restartWithCurrentField() {
    snake.bodyOfSnake = listOf(Pair(1, 1))
    condition = "норм"
    move = ""
}

fun restart() {
    snake.bodyOfSnake = listOf(Pair(1, 1))
    condition = "норм"
    move = ""
    walls = Walls().newWalls()
    apple = Fruit()
    lemon = Fruit(name = "lemon")
    watermelon = Fruit(name = "watermelon")
}