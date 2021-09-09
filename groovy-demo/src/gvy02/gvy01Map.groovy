package gvy02

// 字典 Map

def map = ['key1':'Grovy','key2':'Java']
println('【Map输出】'+map)
println('【Map转字符】'+map.toString())
println('【Map元素个数为】'+map.size())

for (String s:map.keySet()){
    println('【遍历】'+'Key='+s+' Value = '+map.get(s))
}




