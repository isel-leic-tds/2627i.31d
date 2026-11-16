package console

import model.*
//import storage.TextFileStorage
import storage.mongo.MongoDriver
import storage.mongo.MongoStorage

fun main() {
    var game: Game? = null
    //val gs: GameStorage = TextFileStorage("games", GameSerializer)
    MongoDriver("galo").use {
        val gs: GameStorage = MongoStorage("games",it, GameSerializer)
        val cmds: Map<String, Command> = getCommands(gs)
        while (true) {
            val (name, args) = readCommand()
            val cmd = cmds[name]
            if (cmd == null) println("Invalid command $name")
            else try {
                game = cmd.execute(args, game)
                if (cmd.isTerminate) break
                game?.show()
            } catch (e: IllegalArgumentException) {
                println("${e.message}. Use: $name ${cmd.syntax}")
            } catch (e: IllegalStateException) {
                println(e.message)
            }
        }
    }
}