package gvy01


// 这是注释：单行注释//

/*
   这是注释：多行注释
*/

/** 这是注释：文档注释*/

/*
Java中标识符使用的是字母、数字、_、$ 组合模式，但不能使用数字开头、关键字，
在Groovy中对变量定义简单通过 def 关键字来完成
*/

def msg = 'Groovy'
println(msg+" ,对应的类型是 "+ msg.class)

def str = 'Groovy \t'

print(" str "+ str * 3)