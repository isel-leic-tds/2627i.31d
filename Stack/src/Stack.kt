
class Stack<T> private constructor(private val head: Node<T>?): Iterable<T> {
    private class Node<E>(val value: E, val next: Node<E>?)
    private val first: Node<T> get() =
            head ?: throw NoSuchElementException("stack empty")

    constructor(): this(null)
    fun push(elem: T) = Stack(Node(elem, head))
    val top: T get() = first.value
    fun pop() = Stack(first.next)
    fun pop2() = first.let{ Stack(it.next) to it.value } // = pop() to top
    fun isEmpty() = head == null

    fun forEach(action: (T)->Unit) = doAction(head,action)
    private tailrec fun doAction(n: Node<T>?, a: (T)->Unit) {
        if (n==null) return
        a(n.value)
        doAction(n.next,a)
    }

    private class It<E>(var n: Node<E>?): Iterator<E> {
        override fun hasNext() = n != null
        override fun next() =
            (n ?: throw NoSuchElementException("no more elements"))
                .also { n = it.next }.value
    }
    override fun iterator(): Iterator<T> = It(head)
}