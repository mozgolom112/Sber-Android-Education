package Spice

import Aquarium.Spice

fun main(args: Array<String>) {
    buildSpice()
}

fun buildSpice(){
    val mySpice = SimpleSpice()
    val mySpices = listOf(Spice("salt", "nothing"), Spice("curry", "milt"), Spice("sw", "spicy"), Spice("wow"))
    val notSpicy = mySpices.filter{it.heat < 7}

    println()

    println("${mySpice.name} " +
            "${mySpice.heat}")
}