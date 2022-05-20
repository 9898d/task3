class PlayingField(val width: Int = 16, val height: Int = 16) {

    private val listOfCells = mutableListOf<Pair<Int, Int>>()

    fun newCells(): MutableList<Pair<Int, Int>> {
        for (i in 1..width) {
            for (j in 1..height) {
                listOfCells.add(Pair(i, j))
            }
        }
        listOfCells.remove(Pair(1, 1))
        return listOfCells
    }
}