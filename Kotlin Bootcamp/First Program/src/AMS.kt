import java.util.*
import kotlin.math.tan

fun main(args: Array<String>) {

/*
println("Hello ${args[0]}!")
    val temperature = 10
    val isHot = if (temperature > 50) true else false
    println(isHot)

    val message = "You are ${ if (temperature > 50) "fried" else "safe"} fish"
    println(message)

    println("${if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 12) "Good morning" else "Good night"}, ${args[0]}")
*/

    feedTheFish()
    /*
    println(canAddFish(10.0, listOf(3,3,3)))
    println(canAddFish(8.0, listOf(2,2,2), hasDecorations = false))
    println(canAddFish(9.0, listOf(1,1,3), 3))
    println(canAddFish(10.0, listOf(), 7, true))

     */
}

fun getDirtySensorReading() = 20

fun dayOfWeek(){
    println("What day is it today?")
    val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    println(when (dayOfWeek){
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "error"
    })

}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish eat $food")

    if (shouldChangeWater(day)){
        println("Change water today")
    }
}

fun randomDay() : String {
    val week = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(7)]
}

fun fishFood (day : String) : String {
    return when(day){
        "Monday" -> "flakes"
        "Tuesday" -> "pellets"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquitoes"
        "Saturday" -> "lettuce"
        "Sunday" -> "plankton"
        else -> "fasting"
    }

}

fun shouldChangeWater(
    day: String,
    temperature: Int = 22,
    dirty: Int = getDirtySensorReading()) : Boolean{
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false

    }
}

fun isTooHot(temperature: Int) = temperature > 30

fun isDirty(dirty: Int) = dirty > 30

fun isSunday(day: String) = day == "Sunday"

fun canAddFish(tankSize: Double,
                currentFish: List<Int>,
                fishSize: Int = 0,
                hasDecorations: Boolean = true): Boolean{
    /*
    var currentLoad: Int = 0
    for(i in currentFish) currentLoad += i

    return if (hasDecorations) tankSize.times(0.8).minus(currentLoad).minus(fishSize)>0
            else tankSize.minus(currentLoad).minus(fishSize)>0
    */
    return (tankSize * if (hasDecorations) 0.8 else 1.0) >= (currentFish.sum()+fishSize)
}