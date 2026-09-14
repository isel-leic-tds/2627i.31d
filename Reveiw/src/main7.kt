fun main() {
    val r: IntRange = 1..10
    val r2: IntRange = 1..<10
    val r3 = 1.2..7.3
    val r4 = 'a'..'z'
    val r5 = "Ana".."Zé"
    println(r.count())
    if (5.0 in r3) {
        println("5.0 está no intervalo")
    } else {
        println("5.0 não está no intervalo")
    }
    println(r2.first)
    println(r5.start)
    for(i in r4) {
        print("$i ")
    }
    for(i in 10 downTo 3 step 2) {
        print("$i ")
    }
}
