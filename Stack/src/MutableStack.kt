class MutableStack<T> {
    private class Node<E>(val elem:E, val next: Node<E>?)
    private var head: Node<T>? = null
    private val first: Node<T>
        get() = head ?: throw NoSuchElementException("Stack empty")

    fun push(item: T) { head = Node(item, head) }
    fun pop(): T = first.also { head = it.next }.elem
    fun isEmpty(): Boolean = head == null
    val top: T get() = first.elem

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MutableStack<*>) return false
        var n1 = head
        var n2 = other.head
        while (n1 != null && n2 != null) {
            if (n1.elem != n2.elem) return false
            n1 = n1.next
            n2 = n2.next
        }
        return n1 == null && n2 == null
    }
    override fun hashCode() : Int {
        var result = 0
        var n = head
        while (n != null) {
            result = 31 * result + n.elem.hashCode()
            n = n.next
        }
        return result
    }
}