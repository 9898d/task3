class Apple(var coordinates: Pair<Int, Int> = Pair((1..width).random(), (1..height).random())) {
    val appleX = coordinates.first
    val appleY = coordinates.second
}