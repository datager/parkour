package com.example.myfisrtapp

import kotlin.math.max

fun main() {
    println("Hello world")
//    val a = 10
//    println("a = " + a)
//    var a : Int = 10
//    a *= 10
//    println("a = $a")
    val a = 37
    val b = 40
    val value = largerNumber(a,b)
    println("larger number is $value")
}

fun methodName(param1: Int, param2: Int): Int {
    return 0
}

fun largerNumber(num1: Int, num2: Int) = max(num1, num2)