package gvy04

import java.util.concurrent.CountDownLatch

// JUC 定义两个线程，让线程1 必须要等待线程2 执行完毕后 才能执行
CountDownLatch countDownLatch = new CountDownLatch(1)

def firstThread = Thread.start {
    countDownLatch.await()
    println('[The First Thread] '+ Thread.currentThread().getName())
}

def secondThread = Thread.start {
    sleep(1000)
    println('[The Second Thread] '+ Thread.currentThread().getName())
    countDownLatch.countDown()
}
// 第一个线程先启动
firstThread
secondThread