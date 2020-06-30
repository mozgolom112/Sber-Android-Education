object MobyDickWhale {
    val author = "Herman Melville"

    fun jump(){
        // ...
    }
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    YELLOW(0xFFFF00)
}

sealed class  Seal

class SeaLion: Seal()
class Wick: Seal()



fun main() {
    println(Color.RED)
    println(Color.GREEN)
}