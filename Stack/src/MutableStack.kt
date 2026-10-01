class MutableStack<T> {
    private val items = mutableListOf<T>()
    fun push(item: T) { items.addLast(item) }
    fun pop(): T = top.also { items.removeLast() }
    fun isEmpty(): Boolean = items.isEmpty()
    val top: T get() = items.last()
}