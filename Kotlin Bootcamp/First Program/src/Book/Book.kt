package Book

import kotlin.random.Random

const val MAX_NUMBER_OF_BOOKS = 5

fun Book.weight(): Double = pages * 1.5

fun Book.tornPages(torn: Int) {
    if (pages >= torn) pages -= torn else pages = 0}

fun Book.print() = println(pages)

class Puppy{
    fun playWithBook(book: Book){
        book.tornPages(Random.nextInt(12))
    }
}

open class Book(val title: String, val author: String, var pages: Int = 50) {

    private var currentPage : Int = 1

    companion object{
        const val BASE_URL = "http://SOME_URL/"
    }

    open fun readPage(){
        currentPage++
    }

    fun canBorrow(hasBook: Int): Boolean = hasBook < MAX_NUMBER_OF_BOOKS

    fun printUrl() {
        println("${BASE_URL}${title}.html")}
}

class eBook(title: String, author: String, var format: String = "text") : Book(title, author){
    private var wordCount : Int = 0

    override fun readPage() {
        wordCount = wordCount + 250
    }

}

class BasicBook(title: String, author: String, val year: Int = 1999)
    :Book(title, author) {
    fun mainInfoAboutBook(): Pair<String, String> = (title to author)
    fun moreInfoAboutBook(): Triple<String, String, Int> = Triple(title, author, year)
}



fun main() {
    val classic = BasicBook("Evgeniy Onegin","Pushkin",1830)
    println(classic.mainInfoAboutBook().toString())
    println(classic.moreInfoAboutBook().toString())
    classic.printUrl()
    val (title, author, year) = classic.moreInfoAboutBook()
    println("Here is your book $title written by $author in $year.")

    val author1 = "Pushkin"
    val author2 = "Pushkin2"

    val allBooks = setOf("Evg Oneg", "Pik dama")
    val allBooks2 = setOf("Evg Oneg", "Pik dama","32")

    val library = mapOf(author1 to allBooks, author2 to allBooks2)

    println(library.any {it.value.contains("32")})

    val moreBook = mutableMapOf("Rubt" to author1)
    moreBook.put("w", author2)

    moreBook.getOrPut("Areal"){"Tarmashev"}
    moreBook.getOrPut("Areal"){"Wow"}
    println(moreBook)

    val puppy = Puppy()
    val book = Book("Oliver Twist", "Charles Dickens", 540)

    while (book.pages > 0) {
        puppy.playWithBook(book)
        book.print()
    }

}