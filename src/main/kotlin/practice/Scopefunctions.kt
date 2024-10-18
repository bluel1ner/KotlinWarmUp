package practice

fun main(args: Array<String>) {
    letFunctionTest()
}

fun letFunctionTest() {
    listOf(1,2,3).let {
        var subList = it.subList(0, 2)
        println(subList)
        println(it)

        var rec : Rectangle = Rectangle(1,2)
        println(rec.area)
    }
}


class Rectangle(val width: Int, val height: Int) {
//    val area: Int // property type is optional since it can be inferred from the getter's return type
//        get() = this.width * this.height
    val area get() = this.width * this.height
}