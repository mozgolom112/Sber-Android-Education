fun main(args: Array<String>) {
    println("How do you feel?\n")
    println(whatShouldIDoToday(mood = readLine().toString(), temperature = 0))
}

fun whatShouldIDoToday(mood: String, weather: String = "rainy", temperature: Int = 24) : String {
    return when {
        isHappy(mood) && isSunny(weather) -> "go for a walk"
        mood == "sad" && isRainy(weather) && isCold(temperature) -> "stay in bed"
        isVeryHot(temperature) -> "go swimming"
        else -> "Stay home and code."
    }
}

fun isVeryHot(temperature: Int) = temperature > 35

fun isCold(temperature: Int) = temperature == 0

fun isRainy(weather: String) = weather == "rainy"

fun isSunny(weather: String) = weather == "sunny"

fun isHappy(mood: String) = mood == "happy"