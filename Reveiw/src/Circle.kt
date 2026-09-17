const val PI = 3.141592653589793

class Circle(val radius: Double = 0.0) {
    val area: Double
        get() = PI * radius * radius
    //fun getArea() = PI * radius * radius
}

fun main() {
    val c = Circle(1.0)
    println(c.area)
    //println(c.getArea())
    print(c.radius)
}