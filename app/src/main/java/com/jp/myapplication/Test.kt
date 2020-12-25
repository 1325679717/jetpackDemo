package com.jp.myapplication

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
//    launch {
//        val  t = async { f() }
//        t.await()
//    }
    try {
        print(failedConcurrentSum())
    } catch(e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}
suspend fun f(){
    try {
        delay(Long.MAX_VALUE) // 模拟一个长时间的运算
    }finally{
        print("e")
    }
    print("abc")
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // 模拟一个长时间的运算
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}