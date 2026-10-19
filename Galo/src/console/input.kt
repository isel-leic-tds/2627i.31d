package console

data class CommandLine(val name: String, val args: List<String>)

tailrec fun readCommand(): CommandLine {
    print("> ")
    val cmd = readln().trim().uppercase().split(' ').filter(String::isNotEmpty)
    return if (cmd.isNotEmpty())
        CommandLine(cmd[0], cmd.drop(1))
    else readCommand()
}