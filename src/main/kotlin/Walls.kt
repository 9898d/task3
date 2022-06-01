class Walls(private val count: Int = (0..playingField.width).random()) {

    private val walls = mutableListOf<Pair<Int, Int>>()

    fun newWalls(): MutableList<Pair<Int, Int>> {
        for (i in 1..count) {
            val wall = freeCells.subList(1, freeCells.size).random()
            walls.add(wall)
            freeCells.remove(wall)
        }
        return walls
    }
}