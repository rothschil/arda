package gvy04

/**
 * 1、多线程的定义
 */

println('【主线程】'+Thread.currentThread().getName())

// 1.1 start 模式
Thread.start {
    println('【子线程-start】'+Thread.currentThread().getName())
    'Groovy Project :https://gitee.com/rothschil/pippin'.each {
        print(it)
    }
    println('\n')
}

println('https://www.github.com/rothschil/pippin')

// 1.2 startDaemon 模式
Thread.startDaemon {
    println('【子线程-startDaemon】'+Thread.currentThread().getName())
    'Groovy Project :https://gitee.com/rothschil/'.each {
        print(it)
    }
    println('\n')
}