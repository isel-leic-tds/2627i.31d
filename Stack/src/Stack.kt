
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

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private var n: Node<T>? = head
        override fun hasNext() = n != null
        override fun next() =
            (n ?: throw NoSuchElementException("no more elements"))
                .also { n = it.next }.value
    }
}