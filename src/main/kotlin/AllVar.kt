val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)

var snake = Snake()

val playingField = PlayingField()

val height = playingField.height

val width = playingField.width

var apple = Apple()

val walls = Walls().newWalls()

var speed: Long = 250

var condition = ""

var move = ""