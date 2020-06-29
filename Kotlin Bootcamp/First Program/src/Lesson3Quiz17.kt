fun main(args: Array<String>) {
    val spices = listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper" )

    //Create a filter that gets all the curries and sorts them by string length.
    spices.filter { it.contains("curry") }.sortedByDescending { it.length }

    //Filter the list of spices to return all the spices that start with 'c' and end in 'e'. Do it in two different ways.
    spices.filter { it.first()=='c' && it.last() =='e' }
    spices.filter { {it.startsWith('c') && it.endsWith('e') }

    //Take the first three elements of the list and return the ones that start with 'c'.
    spices.take(3).filter {it.startsWith('c')}
}