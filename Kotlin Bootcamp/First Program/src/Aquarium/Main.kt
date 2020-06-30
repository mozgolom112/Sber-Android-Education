package Aquarium

fun main(args: Array<String>) {
    //buildAquarium()
    //buildSpice()
    //fishExample()
    makeFish()
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

fun feedFish(fish: FishAction){
    fish.eat()
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()
    println("Shark: ${shark.color} Plecostomus: ${pleco.color}")

    shark.eat()
    pleco.eat()

    feedFish(shark)
    feedFish(pleco)
}