package Aquarium

fun main(args: Array<String>) {
    //buildAquarium()
    buildSpice()
    //fishExample()
}

fun buildAquarium() {
    val myAquarium = Aquarium()

    myAquarium.width = 23
    myAquarium.length = 40
    myAquarium.height = 233

    println("${myAquarium.volume}")
    myAquarium.volume = 100
    println("${myAquarium.volume}")
    println("${myAquarium.height}")

    val smallAquarium = Aquarium(length = 20, width = 15, height = 30)

    val  myAquarium2 = Aquarium(numberOfFish = 23)
    println("${myAquarium2.volume}")
    println("${myAquarium2.height}")
    println("${myAquarium2.water}")

    println("A2 vol: ${myAquarium2.volume}")
    println("A2 l: ${myAquarium2.length}")
    println("A2 w: ${myAquarium2.width}")
    println("A2 h: ${myAquarium2.height}")
    println("A2 water: ${myAquarium2.water}")
}

fun buildSpice(){
    val mySpice = SimpleSpice()
    val mySpices = listOf(Spice("salt","nothing"),Spice("curry", "milt"), Spice("sw","spicy"),Spice("wow"))
    val notSpicy = mySpices.filter{it.heat < 7}

    println()

    println("${mySpice.name} " +
            "${mySpice.heat}")
}
