enum class Directions(){
    NORTH, SOUTH, EAST, WEST,
    START, END
}

class Game(){
    var path = mutableListOf(Directions.START)

    val north = { path.add(Directions.NORTH) }
    val south = { path.add(Directions.SOUTH) }
    val east = { path.add(Directions.EAST) }
    val west = { path.add(Directions.WEST) }
    val end = { path.add(Directions.END); println("Game Over"); println(path.joinToString("->"))
        path.clear()
        false
    }

    fun move(where: () -> Unit){
        where()

    }
    //можно и так, чтобы напрямую передать пораметры
    fun moveV2(where: () -> Boolean) {
        where()
    }

    fun moveV3(where: () -> Any):Unit{
        where()

    }

    fun makeMove(direction: String? = null): Unit{
        //if (direction in listOf("n","s","e","w")) //создаем новый лист
        when(direction){
                    "n" -> move {north}
                    "s" -> moveV2(south)
                    "e" -> moveV3(east)
                    "w" -> moveV3(west)
                  else -> moveV3(end)
                }
        }
}



fun main() {
    val game = Game()

    while (true){
        print("Enter a direction: n/s/e/w:")
        game.makeMove(readLine())

    }
}