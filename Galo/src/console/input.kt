package console

data class CommandLine(val name: String, val args: List<String>)

tailrec fun readCommand(input: ()->String = ::readln): CommandLine {
    print("> ")
    val cmd = input().trim().uppercase().split(' ').filter(String::isNotEmpty)
    return if (cmd.isNotEmpty())
        CommandLine(cmd[0], cmd.drop(1))
    else readCommand(input)
}