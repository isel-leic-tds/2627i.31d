fun main() {
    val letter: Char = 'A'
    val tab = '\t'
    val enter = '\n'
    val euro = '\u20AC'
    println("$letter$tab$enter$euro")

    val a = 'a'.code
    val c = 'a' + 2
    val dif = 'c' - 'a'
    val sum = 'a'.code + 'b'.code

    val s = "Hello, World!"
    val s1 = """\n
        Hello, 
        World!
    """
    println(s1)
}