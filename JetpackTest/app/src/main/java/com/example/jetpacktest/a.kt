package com.example.jetpacktest

fun printFruits() {
    val fruitList: MutableList<String> = ArrayList()
    fruitList.add("Apple")
    fruitList.add("Banana")
    fruitList.add("Orange")
    fruitList.add("Pear")
    fruitList.add("Grape")
    for (fruit in fruitList) {
        println(fruit)
    }
}