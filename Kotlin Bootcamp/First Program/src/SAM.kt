interface Runnable{
    fun run()
}

interface Callable<T>{
    fun call(): T
}

fun example(){
    val runnable = object : Runnable {
        override fun run() {
            println("I'm runnable")
        }
    }
    //Почему не работает?
    /*
    JavaRun.runNow {
        println("Passing lambda")
    }
     */

    JavaRun.runNow(runnable) //вызов java


}
fun main(args: Array<String>) {
    example()
}