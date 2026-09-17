
data class Point(val x: Double = 0.0, val y: Double = 0.0) {
    /*
    override fun toString() = "Point(x=$x, y=$y)"
    override fun equals(other: Any?) = other is Point && other.x == x && other.y == y
    override fun hashCode() = x.hashCode() * 31 or y.hashCode()
    */
}
val origin = Point()

class MovingCircle(val radius: Double = 0.0, at: Point) {
    var center: Point = at
        private set
    fun move(dx: Double, dy: Double) {
        center = Point(center.x + dx, center.y + dy)
    }
}

val MovingCircle.area: Double
    get() = PI * radius * radius

val String.size: Int get() = length

fun main() {
    val circle = MovingCircle(1.0, Point())
    println("circle(center=${circle.center}, area=${circle.area})")
    println(circle.center == origin)
    circle.move(1.0, 1.0)
    println("circle(center=${circle.center}, area=${circle.area})")
    //circle.center = Point(1.0, 1.0)
}