interface Stack<T>: Iterable<T> {
    val top: T
    fun isEmpty(): Boolean
    fun push(e: T): Stack<T>
    fun pop(): Stack<T>
}

private object StackEmpty: Stack<Any> {
    private fun throwEmpty(): Nothing { throw NoSuchElementException("stack empty") }
    override val top get() = throwEmpty()
    override fun isEmpty() = true
    override fun push(e: Any): Stack<Any> = StackNotEmpty(e,this)
    override fun pop(): Nothing = throwEmpty()
    override fun iterator(): Iterator<Any> = object : Iterator<Any> {
        override fun hasNext() = false
        override fun next() = throwEmpty()
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Stack(): Stack<T> = StackEmpty as Stack<T>

private class StackNotEmpty<T>(
    override val top: T,
    private val tail: Stack<T>
): Stack<T> {
    override fun isEmpty() = false
    override fun push(e: T): Stack<T> = StackNotEmpty(e,this)
    override fun pop(): Stack<T> = tail
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private var curr: Stack<T> = this@StackNotEmpty
        override fun hasNext() = !curr.isEmpty()
        override fun next(): T {
            if (curr.isEmpty()) throw NoSuchElementException("no more elements")
            val res = curr.top
            curr = curr.pop()
            return res
        }
    }
    override fun equals(other: Any?): Boolean =
        other is StackNotEmpty<*> && top == other.top && tail.equals(other.tail)

    override fun hashCode() = fold(0){ res, e -> res * 31 + e.hashCode() }
}
/*
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

    override fun equals(other: Any?): Boolean {
        if (other !is Stack<*>) return false
        val i = other.iterator()
        for(e in this)
            if (! i.hasNext() || e != i.next()) return false
        return !i.hasNext()
    }

    override fun hashCode() =
        this.fold(0){ res, e -> res * 31 + e.hashCode() }
}*/
