package gvy04

// 定时任务
new Timer('[Timer]').runAfter(1000){
    for (int i=0;i<10;i++){
        println('【定时任务】'+Thread.currentThread().getName()+ ' Groovy Project :https://gitee.com/rothschil/pippin')
    }
}