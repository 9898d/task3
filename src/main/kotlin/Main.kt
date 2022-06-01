import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlin.concurrent.timer


@ExperimentalComposeUiApi
fun main() = application {
    timer(
        daemon = true, period = speed,
    ) {
        when (condition) {
            "норм" -> {
                if (headColor != Color.Blue) {
                    headColor = Color.White
                }
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
                if (headColor != Color.Blue) {
                    headColor = Color.Yellow
                }
                timerFast
            }
            "медленно" -> {
                if (headColor != Color.Blue) {
                    headColor = Color(255, 0, 255)
                }
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