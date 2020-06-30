package Spice

import Color

sealed abstract class Spice(val name: String, val spiceness: String = "mild", color: SpiceColor): SpiceColor by color {

    abstract fun prepareSpice()

}

//fun makeSalt() = Spice("Salt")

class Curry(name: String, spiceness: String = "mild", color: SpiceColor = YellowSpiceColor):
        Spice(name, spiceness, color),
        //SpiceColor by color,
        Grinder
{

    //override val heat = heat

    override fun grind() {
        println("grind")
    }

    override fun prepareSpice() {
        grind()
    }

}

interface Grinder {
    fun grind()
}

interface SpiceColor{
    val color: Color
}

object YellowSpiceColor: SpiceColor {
    override val color: Color
        get() = Color.YELLOW
}

data class SpiceContainer(val spice: Spice){val label = spice.name}



fun main() {
    val curry = Curry(name = "curry")
    println(curry.color)
    //println(curry.heat)

    val spiceCabinet = listOf(SpiceContainer(Curry("Yellow Curry", "mild")),
            SpiceContainer(Curry("Red Curry", "medium")),
            SpiceContainer(Curry("Green Curry", "spicy")))

    for(element in spiceCabinet) println(element.label)
}