fun main(args: Array<String>) {
    for(i in 1..10) {
        var result = getFortuneCookie(getBirthday())
        print("Your fortune is: ${result}")

        if (result.contains("Take it easy")) break
    }
}

fun getFortuneCookie(birthday : Int) : String {
    val fortunes = listOf("You will have a great day!",
        "Things will go well for you today.",
        "Enjoy a wonderful day of success.",
        "Be humble and all will turn out well.",
        "Today is a good day for exercising restraint.",
        "Take it easy and enjoy life!",
        "Treasure your friends because they are your greatest fortune.")

    return when(birthday){
        in 1..7 -> fortunes[3]
        28, 31 -> "Ouw. You lucky"
        else ->  fortunes[birthday.rem(fortunes.size)]
    }

}

fun getBirthday(): Int {
    println("Enter your birthday: ")

    var birthday = readLine()?.toIntOrNull() ?: 1
    if (birthday > 31 || birthday < 1) birthday = 1

    return birthday
}