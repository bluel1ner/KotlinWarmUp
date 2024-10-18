package practice

import practice.model.Statistics

fun main(args: Array<String>) {
//    var b: String? = null
//// Re-assigns null to the nullable variable
//// Tries to directly return the length of a nullable variable
//    val l = b?.length
//    println(l)
//
//    var nullableValue: String? = "Kotlin"
//    nullableValue = null
//    println(nullableValue?.length)
//
//    val bb: String? = null
//// Treats b as non-null and accesses its length
//    val ll = bb?.length
//    println(ll)
//
//    letFunction()
//    safeCastsFunction()
//    structuralEquality()
    arrayEqualityFunc()
}


fun letFunction() {
    val stat1: Statistics? = null
    val stat2: Statistics? = Statistics(1, 2)
    // let allow to perform operations only on non-null types
    val list = listOf(stat1, stat2)
    list.forEach { it -> it?.let { println(it) } }
    for (el in list) {
        el?.let { println(el) }
    }
}

fun safeCastsFunction() {
//    as? operator for safe casts
    val a: Any? = null
    val aInt: Int? = a as? Int
    val aString: String? = a as? String
    println(aInt)
    println(aString)
}

fun structuralEquality() {
    var a = "hello"
    var b = "hello"
    var c = null
    var d = null
    var e = d
    // == translated like a?.equals(b) ?: (b === null)
    println(a == b)
    // true
    println(a == c)
    // false
    println(c == e)
    // true
}

fun referentialEqualityFunc() {
    var a = "Hello"
    var b = a
    var c = "world"
    var d = "world"

    println(a === b)
    // true
    println(a === c)
    // false
    println(c === d)
    // true
}

fun arrayEqualityFunc() {
    val arr1: Array<Int> = arrayOf(1, 2, 3)
    val arr2: Array<Int> = arrayOf(1, 2, 3)
    println(arr1.contentEquals(arr2))
    // reference equality
    println(arr1 == arr2)
}