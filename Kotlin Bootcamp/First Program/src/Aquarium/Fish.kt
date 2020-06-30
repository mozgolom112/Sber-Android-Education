package Aquarium

class Fish(var friendly: Boolean = true, volumeNeeded: Int) {

    val size: Int

    init {
        println("first init block")
    }

    constructor() : this(true, 8){
        println("running secondary constructor")
    }

    init {
        if(friendly){
            size = volumeNeeded
        } else {
            size = volumeNeeded * 2
        }
    }

    init {
        println("constructed fish of size $volumeNeeded final size ${this.size}")
    }

    init {
        println("last init block")
    }
}

fun makeDefaultFish() = Fish(true, 50)

fun  fishExample() {
    val fish = Fish()
    //println("Is the fish friendly? ${fish.friendly}. It need volume ${fish.size}")
}