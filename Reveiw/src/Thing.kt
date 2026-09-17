class Thing: Any() {
    override fun toString() = "Thing"
        //(this::class.simpleName?:"*") + "@" + hashCode().toString(16)
    override fun equals(other: Any?) = true
    override fun hashCode() = 42
}

fun main() {
    val t1 = Thing()
    val t2 = Thing()
    println("$t1: ${t1.hashCode()}")
    println("$t2: ${t2.hashCode()}")
    print(t1 == t2)
}