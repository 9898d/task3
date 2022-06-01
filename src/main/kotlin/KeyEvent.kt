import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*

@ExperimentalComposeUiApi
fun keyEvent(it: KeyEvent) {
    if (it.type == KeyEventType.KeyUp) {
        when (it.key) {
            Key.DirectionDown -> {
                if (move != "вверх") {
                    move = "вниз"
                    rotate = 180f
                }
            }
            Key.DirectionUp -> {
                if (move != "вниз") {
                    move = "вверх"
                    rotate = 0f
                }
            }
            Key.DirectionRight -> {
                if (move != "влево") {
                    move = "вправо"
                    rotate = 90f
                }
            }
            Key.DirectionLeft -> {
                if (move != "вправо") {
                    move = "влево"
                    rotate = 270f
                }
            }
            Key.Spacebar -> {
                move = "пауза"
            }
            Key.A -> {
                condition = "медленно"
            }
        }
    }
}