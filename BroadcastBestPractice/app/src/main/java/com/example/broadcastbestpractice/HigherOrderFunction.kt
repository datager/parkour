package com.example.broadcastbestpractice

class HigherOrderFunction {
    fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
        block()
        return this
    }
}

fun printString(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}

fun main() {
    println("main start")
    val str = ""
    printString(str) { s ->
        println("lambda start")
        if (s.isEmpty()) return@printString
        println(s)
        println("lambda end")
    }
    println("main end")
}