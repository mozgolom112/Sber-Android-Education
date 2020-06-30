package Aquarium

fun main() {
    val symptoms = mutableListOf<String>("white spots", "red spots", "not eating", "bloated", "belly up")

    symptoms.add("white fungus")
    symptoms.remove("white fungus")

    println(symptoms.subList(4,symptoms.size))

    listOf(1, 5, 3).sum()

    println(listOf("a", "b", "cat").sumBy { it.length })

    val cures = mapOf("white spots" to "Ich", "red spots" to "hole disease")

    println(cures.get("white spots"))
    println(cures["red spots"])

    println(cures.getOrDefault("bloating", "sorry I don't know")) //просто возращает значение, не добавляя его в map,
    //тем более что mapOf - immutable, и он вообще изменять не должен)
    println(cures["bloating"])
}

class AquariumCare(){

}