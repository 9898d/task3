import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*

@ExperimentalComposeUiApi
fun keyEvent(it: KeyEvent) {
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
        }
    }
}