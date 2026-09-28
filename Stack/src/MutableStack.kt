class MutableStack<T> {
    private var items = emptyList<T>()
    fun push(item: T) { items = items + item }
    fun pop(): T {
        val t = top
        items = items.dropLast(1)
        return t
    }
    fun isEmpty(): Boolean = items.isEmpty()
    val top: T get() = items.last()
}