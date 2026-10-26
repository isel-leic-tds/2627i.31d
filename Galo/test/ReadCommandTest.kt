import console.readCommand
import kotlin.test.*

class ReadCommandTest {
    @Test
    fun testReadCommand() {
        val commandLine = readCommand({ "ls -l" })
        assertEquals("LS", commandLine.name)
        assertEquals(["-L"], commandLine.args)
    }
    @Test
    fun testReadCommandWithEmptyInput() {
        val lines = ["", "ls -l"]
        var i = 0
        val commandLine = readCommand({ lines[i++] })
        assertEquals("LS", commandLine.name)
        assertEquals(["-L"], commandLine.args)
    }
}