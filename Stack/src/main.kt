fun main() {
    val stk = MutableStack<Char>()
    stk.push('A')
    stk.push('B')
    stk.push('C')
    println(stk.top)  // -> C
    println(stk.pop())// -> C
    println(stk.top)  // -> B
    while(!stk.isEmpty())
        println(stk.pop()) // -> B,A
}