class MutableStack<T> {
    private class Node<E>(val elem: E, val next: Node<E>?)

    private var head: Node<T>? = null
    private val first: Node<T>
        get() = head ?: throw NoSuchElementException("Stack empty")

    fun push(item: T) { head = Node(item, head) }
    fun pop(): T = first.also { head = it.next }.elem
    fun isEmpty() = head == null
    val top: T get() = first.elem

    override fun equals(other: Any?) =
        other is MutableStack<*> && equalsNodes(head, other.head)

    private tailrec fun equalsNodes(n1: Node<T>?, n2: Node<*>?): Boolean =
        when {
            n1 == null && n2 == null -> true
            n1 == null || n2 == null -> false
            n1.elem != n2.elem -> false
            else -> equalsNodes(n1.next, n2.next)
        }

    override fun hashCode() = computeHash(head, 0)

    private tailrec fun computeHash(n: Node<T>?, res: Int): Int =
        when (n) {
            null -> res
            else -> computeHash(n.next, 31 * res + n.elem.hashCode())
        }
}