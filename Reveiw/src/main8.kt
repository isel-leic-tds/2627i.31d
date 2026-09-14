fun main() {
    val l = listOf(1, 2, 3, 4, 5)
    //l.count()
    for (s in ["Ana", "Bia", "Clara"]) {
        print("$s ")
    }
    //l = l + 4
    println(l + 4)
    println( l - 4 )
    println( l + listOf(2,3))

    val lm = mutableListOf(1, 2, 3, 4, 5)
    lm.add(6)
    lm.remove(3)
    println(lm)
}