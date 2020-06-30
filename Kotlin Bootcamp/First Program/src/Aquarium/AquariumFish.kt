package Aquarium

fun main(args: Array<String>) {
    delegate()

}

fun delegate(){
    val pleco = Plecostomus(RedColor)
    println("Fish has color ${pleco.color}")
    pleco.eat()
    pleco.color
}

abstract class AquariumFish: FishAction {
    abstract val color: String
    override fun eat() {
        println("yum")
    }

}

class Shark: AquariumFish(), FishAction{
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}


/*
fishColor - параметр типа интерфейса FishColor. Ему можно присвоить объекты(инициализируются один раз Singleton)
которые имплементируют данный интерфейс. Можно передать как параметр его через конструкцию by
 */
class Plecostomus(fishColor: FishColor = RedColor):
        FishAction by PrintingFishAction("Peco"),
        FishColor by fishColor

interface FishAction {

    fun eat()
}

interface FishColor {
    val color: String
}

object GoldColor: FishColor {
    override val color: String
        get() = "gold"
}

object RedColor: FishColor {
    override val color: String
        get() = "red"
}

class PrintingFishAction(val food: String): FishAction{
    override fun eat() {
        println(food)
    }
}