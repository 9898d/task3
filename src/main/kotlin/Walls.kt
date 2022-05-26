class Walls(val count: Int = (0..64).random()) {

    val walls = mutableListOf<Pair<Int, Int>>()

    val freeCells = playingField.allCells()

    fun newWalls(): MutableList<Pair<Int, Int>> {
        for (i in 1..count) {
            val wall = freeCells.random()
            walls.add(wall)
            freeCells.remove(wall)
        }
        return walls
    }
}