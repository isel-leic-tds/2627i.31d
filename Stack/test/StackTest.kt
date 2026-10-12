import kotlin.test.*

class StackTest {
    @Test fun `Use an empty stack`() {
        val sut = Stack<Int>()
        assertTrue( sut.isEmpty() )
        assertFailsWith<NoSuchElementException> { sut.top }
        assertFailsWith<NoSuchElementException> { sut.pop() }
    }
    @Test fun `Using a regular Stack`() {
        val sut = Stack<Int>().push(1).push(2).push(3)
        assertFalse(sut.isEmpty())
        assertEquals(2, sut.pop().top)
        assertTrue( sut.pop().pop().pop().isEmpty())
    }
    @Test fun `do action to all elements`() {
        val sut = Stack<Int>().push(1).push(2).push(3)
        var sum = 0
        sut.forEach{ sum += it }
        assertEquals(6,sum)

        for(e in sut) sum += e
        assertEquals(12,sum)

        // for(e in A) action(e)
        // -->
        // val it = A.iterator()
        // while ( it.hasNext() ) {
        //    val e = it.next()
        //    action(e)
        // }
    }
    @Test fun `verify equality between Stacks`() {
        val s1 = Stack<Int>().push(1).push(2)
        val s2 = Stack<Int>().push(1).push(2)
        assertEquals(s1,s2)
        assertEquals(s1.hashCode(),s2.hashCode())
        assertNotEquals(s1.pop(),s2)
        assertNotEquals(s1,s2.pop())
    }
}