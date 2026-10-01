import kotlin.test.*

class MutableStackTest {
    @Test
    fun `Create an empty stack`() {
        val sut = MutableStack<Int>()
        assertTrue(sut.isEmpty())
        assertFailsWith<NoSuchElementException> { sut.top }
        assertFailsWith<NoSuchElementException> { sut.pop() }
        sut.push(1)
        assertFalse(sut.isEmpty())
        assertEquals(1, sut.top)
    }
    @Test fun `Use a stack with elements`() {
        val sut = MutableStack<Char>()
        val elems = ['A','B','C','D']
        sut.push(elems.first())
        assertFalse(sut.isEmpty())
        assertEquals(elems.first(), sut.top)
        elems.drop(1).forEach {
            sut.push(it)
            assertEquals(it, sut.top)
        }
        elems.reversed().forEach {
            assertFalse(sut.isEmpty())
            assertEquals(it, sut.pop())
        }
        assertTrue(sut.isEmpty())
    }
}