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

    val empty = Stack<Char>()
    val one = empty.push('X')
    val two = one.push('Y')
    val e = two.top
    val other = two.pop()
    val (other2, top) = two.pop2()
    println(one.top)
    println(two.top)
}