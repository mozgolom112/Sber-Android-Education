fun main(args: Array<String>) {
    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)

    println(numbers.divisibleBy {it.rem(3)})


}

fun List<Int>.divisibleBy(block: (Int)->Int):List<Int>{
    val list = mutableListOf<Int>()

    for(item in this){
        if ( block(item) == 0) list.add(item)
    }

    return list
}

fun myWith(name: String, block: String.()->Unit){
    name.block()
}