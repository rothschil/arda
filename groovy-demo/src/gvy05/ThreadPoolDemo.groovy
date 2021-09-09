package gvy05

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

for(x in 1..20){
    es.submit(()->{
        println('[ThreadPool]'+ Thread.currentThread().getName())
    })
}
