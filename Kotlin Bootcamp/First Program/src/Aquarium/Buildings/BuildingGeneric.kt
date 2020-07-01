package Aquarium.Buildings
/*
open class BaseBuildingMaterial(val numberNeeded: Int = 1)

class Wood(numberNeeded: Int = 4) : BaseBuildingMaterial(numberNeeded)
class Brick(numberNeeded: Int = 8) : BaseBuildingMaterial(numberNeeded)

class Building<T: BaseBuildingMaterial>(val material: T){

    val baseMaterialNeeded = 100
    val actualMaterialNeeded: Int = (baseMaterialNeeded * material.numberNeeded).toInt()

    fun build(){
        println("We need ${actualMaterialNeeded} of ${material::class.simpleName}")
    }
}

fun main() {
    val building = Building<Wood>(Wood())

    building.build()
}

 */

open class BaseMaterialBuilding(open val numberNeeded: Int = 1)

class Wood(): BaseMaterialBuilding() {override val numberNeeded = 4}
class Block(): BaseMaterialBuilding() {override val numberNeeded = 8}

class Building<T: BaseMaterialBuilding>(val material: T){

    val numberOfMaterialNeeded : Int = 100
    val actualMaterialNeeded: Int = (numberOfMaterialNeeded * material.numberNeeded)

    fun build(){
        println("${actualMaterialNeeded} of ${material::class.simpleName} is required")
    }

}

fun <T: BaseMaterialBuilding>isSmallBuilding(building: Building<T>) {
    if (building.actualMaterialNeeded < 500)
        println("small building")
    else
        println("large building")
}

fun main() {
    val building = Building<Wood>(Wood())
    building.build()

}