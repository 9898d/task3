class Snake(var length: Int = 1, private val xOfHead: Int = 1, private val yOfHead: Int = 1) {

    var bodyOfSnake: List<Pair<Int, Int>> = listOf(Pair(xOfHead, yOfHead))


    fun food() {
        length++
    }
}