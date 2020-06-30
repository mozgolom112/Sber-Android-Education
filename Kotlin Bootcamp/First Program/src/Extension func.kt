fun String.hasSpaces(): Boolean {
    val found = this.find {it == ' '}
    return found != null
}

fun String.hasSpacesV2() = find {it == ' '} != null

fun extensionsExample(){
    println("Does it have spaces".hasSpacesV2())
}

open class AquriumPlant(val color: String, private val size: Int)
class GreenLeafyPlant(size: Int) : AquriumPlant("Green", size)

val AquriumPlant.isGreen: Boolean
    get() = color == "green"

fun AquriumPlant.isRed() = color == "Red"

fun AquriumPlant.print() = println("AquriumPlant")
fun GreenLeafyPlant.print() = println("GreenLeafyPlant")

fun AquriumPlant?.pull() {
    this?.apply {
        println("removing $this")
    }
}

fun nullableExample() {
    val aquriumPlant: AquriumPlant? = null
    aquriumPlant.pull()
    val plant = AquriumPlant("green", 50)
    plant.pull()

}

fun staticExample(){
    val plant = GreenLeafyPlant(30)
    plant.print()

    val aquriumPlant: AquriumPlant = plant
    println(aquriumPlant)
    println(aquriumPlant.color)
    aquriumPlant.print()
}

fun propertyExample() {
    val plant = AquriumPlant("green", 50)
    println(plant.isGreen)
}

fun main() {
    extensionsExample()
    println(AquriumPlant("red", 23).isRed())
    staticExample()
    propertyExample()
    nullableExample()
}