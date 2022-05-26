import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Snake(var length: Int = 1, private val xOfHead: Int = 1, private val yOfHead: Int = 1) {

     var bodyOfSnake: List<Pair<Int, Int>> by mutableStateOf(listOf(Pair(xOfHead, yOfHead)))

    fun up() {
        val head = bodyOfSnake.first()
        bodyOfSnake = listOf(Pair(head.first, head.second - 1)) + bodyOfSnake.subList(0, bodyOfSnake.size - 1)
    }

    fun down() {
        val head = bodyOfSnake.first()
        bodyOfSnake = listOf(Pair(head.first, head.second + 1)) + bodyOfSnake.subList(0, bodyOfSnake.size - 1)
    }

    fun right() {
        val head = bodyOfSnake.first()
        bodyOfSnake = listOf(Pair(head.first + 1, head.second)) + bodyOfSnake.subList(0, bodyOfSnake.size - 1)
    }

    fun left() {
        val head = bodyOfSnake.first()
        bodyOfSnake = listOf(Pair(head.first - 1, head.second)) + bodyOfSnake.subList(0, bodyOfSnake.size - 1)
    }
}