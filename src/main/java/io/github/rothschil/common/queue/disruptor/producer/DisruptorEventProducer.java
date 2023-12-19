package io.github.rothschil.common.queue.disruptor.producer;

import com.lmax.disruptor.RingBuffer;
import io.github.rothschil.common.queue.disruptor.event.DisruptorEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisruptorEventProducer {

    private final RingBuffer<DisruptorEvent> ringBuffer;

    public DisruptorEventProducer(RingBuffer<DisruptorEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void publish(String eventMsgBody){
        // ringBuffer是个队列，其next方法返回的是下最后一条记录之后的位置，这是个可用位置
        long next = ringBuffer.next();
        try {
            DisruptorEvent event = ringBuffer.get(next);
            event.setEventMsgBody(eventMsgBody);
        } catch (Exception e) {
            log.error("向RingBuffer队列存入数据[{}]出现异常=>{}", eventMsgBody, e.getStackTrace());
        } finally {
            ringBuffer.publish(next);
        }
    }
}
