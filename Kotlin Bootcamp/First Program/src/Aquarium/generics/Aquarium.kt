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

class Aquarium<out T: WaterSupply>(val waterSupply: T){

    fun addWater(cleaner: Cleaner<T>) {
        if(waterSupply.needsProcessed) {
            cleaner.clean(waterSupply)
        }

        println("adding water from $waterSupply")
    }

    fun someStaff(): T{return waterSupply} //для out
    //fun someStaffWithIn(x: T){} //для in
}

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner: Cleaner<TapWater>{
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemicakCleaners()
    }
}

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>){
    println("aquaium water is clean: ${aquarium.waterSupply.needsProcessed}")
}

inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")

fun genericExample() {

    val aquarium = Aquarium<TapWater>(TapWater())
    aquarium.waterSupply.addChemicakCleaners()
    println(aquarium.hasWaterSupplyOfType<LakeWater>())

    /*
    val aquarium3 = Aquarium<LakeWater>(LakeWater())
    aquarium3.waterSupply.filter()


    val cleaner = TapWaterCleaner()

    aquarium.addWater(cleaner = cleaner)

    addItemTo(aquarium)

    isWaterClean<TapWater>(aquarium)

     */
}

fun main() {
    genericExample()
}