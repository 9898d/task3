class Fruit(val coordinates: Pair<Int, Int> = freeCellsForFruit.random(), val name: String = "Яблоко") {

   /*private val fruits = mutableListOf<Pair<Int, Int>>()

    fun newFruits(): MutableList<Pair<Int, Int>> {
        for (i in 1..4) {
            val fruit = freeCellsForFruit.random()
            fruits.add(fruit)
            freeCellsForFruit = freeCellsForFruit.filter { it != fruit }
        }
        return fruits
    }

    fun newApple() {
        freeCellsForFruit = freeCellsForFruit + fruits.first()
        fruits.removeFirst()
        fruits.add(0, freeCellsForFruit.random())
        freeCellsForFruit = freeCellsForFruit.filter { it != fruits.first() }
    } */
}