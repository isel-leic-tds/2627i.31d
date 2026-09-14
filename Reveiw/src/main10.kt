fun main() {
    val ages: Map<String, UInt> = mapOf(
        "Alice" to 25u,
        "Bob" to 30u,
        Pair("Charlie",35u),
        "Diana".to(28u)
    )
    println(ages["Alice"])
    println(ages.get("Pedro") ?: "Não encontrado")
    val ages2 = ages + ("Eve" to 22u)
    println(ages2)
    println("abc" + 3)
}