package gvy05

// 线程同步
def ticket =10

def sale ={->
    synchronized (this){
        sleep(100)
        println('[Buy tickets]'+Thread.currentThread().getName()+ ' The remaining votes ' +(ticket--))
    }
}

for(x in 1..10){
    def st = Thread.start(sale)
    st.name='TicketSeller -'+x
}