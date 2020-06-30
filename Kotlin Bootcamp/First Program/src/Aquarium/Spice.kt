package Aquarium

class Spice(val name: String, val spiceness: String = "mild") {

    val heat: Int
    get() = when(spiceness){
        "mild" -> 1
        "medium" -> 3
        "spicy" -> 5
        "very spicy" -> 7
        "extremely spicy" -> 10
        else -> 0
    }

    init{
       println("${name} : ${heat}")
    }
}

fun makeSalt() = Spice("Salt")
