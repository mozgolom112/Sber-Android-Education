package Aquarium.generics

open class WaterSupply(var needsProcessed: Boolean)

class TapWater : WaterSupply(true) {

    fun addChemicakCleaners() {
        needsProcessed = false
    }
}

class FishStoreWater : WaterSupply(needsProcessed = false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessed = false
    }
}

class Aquarium<T: WaterSupply>(val waterSupply: T){

    fun addWater() {
        check(!waterSupply.needsProcessed) { "water supply needs processed"}

        println("adding water from $waterSupply")
    }
}

fun genericExample() {

    val aquarium = Aquarium<TapWater>(TapWater())
    aquarium.waterSupply.addChemicakCleaners()

    val aquarium3 = Aquarium<LakeWater>(LakeWater())
    aquarium3.waterSupply.filter()
    aquarium3.addWater()
}

fun main() {
    genericExample()
}