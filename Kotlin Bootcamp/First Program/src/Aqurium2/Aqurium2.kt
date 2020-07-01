package Aqurium2

data class Fish(var name: String)

fun main(args: Array<String>) {
    fishExample()
}

fun fishExample() {
    val fish = Fish("splashy")
    val fish2 = Fish(name = "splashy").apply { name = "Sharky" }
    val interstingCase = Fish(name = "splashy").apply { name = "Sharky" }.run { println(name); this } //без this будет unit
    println(fish2.name)
    println(interstingCase.name)
    myWith (fish.name){
        println(capitalize())
    }

    myWith(fish.name, object : Function1<String,Unit> {
        override fun invoke(name: String) {
            name.capitalize()
        }
    })

    println(fish.run { name })
    println(fish.apply { })

    println(fish.let{it.name.capitalize()}
            .let{it + "fish"}
            .let {it.length }
            .let {it+31})


}

inline fun myWith(name: String, block: String.() -> Unit){
    name.block()
}

