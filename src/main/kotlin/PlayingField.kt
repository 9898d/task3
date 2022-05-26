class PlayingField(val width: Int = 16, val height: Int = 16) {

    fun allCells(): MutableList<Pair<Int, Int>> {
        val a = mutableListOf<Pair<Int, Int>>()
        for (i in 1..width) {
            for (j in 1..height) {
                a.add(Pair(i, j))
            }
        }
        return a
    }

    private val listOfCells = allCells()

}