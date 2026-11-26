
import kotlin.test.*
import storage.*
import model.*

class ClashTest {
    private val storage = TextFileStorage<Name,Game>("testDir", GameSerializer)

    @AfterTest fun after() {
        storage.fs.delete(storage.basePath) // delete the test folder
    }
    @Test fun `use a clash not started`() {
        val clash = Clash(storage)
        assertIsNot<ClashRun>(clash)
        assertFailsWith<IllegalStateException> { clash.play(Position(0)) }
        assertFailsWith<IllegalStateException> { clash.newBoard() }
    }
    @Test fun `start a clash`() {
        val name = Name("test1")
        val clash = Clash(storage).start(name)
        assertIs<ClashRun>(clash)
        assertEquals(name, clash.name)
        assertEquals(Player.X, clash.side)
        assertEquals(Player.X, clash.game.first)
        val game = storage.read(name)
        assertNotNull(game)
        assertIs<Run>(game.state)
        assertEquals(Player.X, game.state.turn)
        clash.deleteIfOwner() // is the owner
        assertNull(storage.read(name))
    }
    @Test fun `join a clash`() {
        val name = Name("test2")
        val _ = Clash(storage).start(name)
        val clash = Clash(storage).join(name)
        assertIs<ClashRun>(clash)
        assertEquals(Player.O, clash.side)
        clash.deleteIfOwner() // is not the owner
        assertNotNull(storage.read(name))
        storage.delete(name)
    }
    @Test fun `play a clash`() {
        val name = Name("test3")
        val _ = Clash(storage).start(name).play(Position(0))
        val game = storage.read(name)
        assertNotNull(game)
        val state = game.state
        assertIs<Run>(state)
        assertEquals(Player.O, state.turn)
        assertEquals(Player.X, game.board[Position(0)])
        storage.delete(name)
    }
    @Test fun `start, join, play and refresh`() {
        val name = Name("test4")
        var c1 = Clash(storage)
        var c2 = Clash(storage)
        c1 = c1.start(name)
        c2 = c2.join(name)
        val e = assertFailsWith<IllegalStateException> {
            c2.play(Position(0))
        }
        assertEquals("Not your turn", e.message)
        val _ = c1.play(Position(0))
        val _ = c2.refresh().play(Position(1))

        storage.delete(name)
    }
}
