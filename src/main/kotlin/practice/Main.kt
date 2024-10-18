package practice

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class Request(
    var url: String,
    var status: Int
)

fun main(args: Array<String>) {
    collectionPractice()
    println(defineTypeOfObj(Request("test", 100)))
}


fun collectionPractice() {
    var strings = listOf<String>("1234", "5678")
    println(strings.flatMap { it.toList() })

    val emails = listOf<String>()
    val theOldestEmail = emails.firstOrNull() ?: "first"
    val theFreshestEmail = emails.lastOrNull() ?: "last"
    println(theFreshestEmail)
    println(theOldestEmail)

    val requestList = listOf(
        Request("url1", 200),
        Request("url1", 300),
        Request("url", 400)
    )

    println(requestList.groupBy(Request::url).keys)

    val listOfDifferentTypes = listOf(null, 1, "2", Request("test", 210))
    listOfDifferentTypes.filterIsInstance<String>()
        .forEach { println(it.uppercase()) }

    val colors = listOf("red", "brown")
    val animals = listOf("fox", "bear", "wolf")
//   zip() returns the List of Pair objects.
    println(colors.zip(animals) { color, animal ->
        "The ${animal.replaceFirstChar { it.uppercase() }} is $color"
    }.lastOrNull() ?: "last")
}


//when expression
fun defineTypeOfObj(obj: Any): String {
    return when (obj) {
        is Long -> "Long"
        is Int -> "Int"
        is String -> "String"
        else -> "cannot define type"
    }
}