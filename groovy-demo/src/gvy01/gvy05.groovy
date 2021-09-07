package gvy01


def hello = {
    return "【当前参数】 " + it
}

// 闭包
def sum ={ int ...args
    ->
    def sum=0
    for(int num :args){
        for (i in num..10 ){
            sum+=i
        }
    }
    return sum
}
println("【求和】"+sum(1,2))
println("【参数】"+hello())
println("【参数】"+hello("Groovy"))