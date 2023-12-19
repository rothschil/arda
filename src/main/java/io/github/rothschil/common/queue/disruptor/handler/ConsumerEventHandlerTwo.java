package io.github.rothschil.common.queue.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import io.github.rothschil.common.queue.disruptor.event.DisruptorEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 具体队列内容处理，独立消费者
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
@Slf4j
public class ConsumerEventHandlerTwo implements EventHandler<DisruptorEvent> {

    private String name;

    public ConsumerEventHandlerTwo(String name) {
        this.name=name;
    }

    @Override
    public void onEvent(DisruptorEvent event, long sequence, boolean endOfBatch) throws Exception {
        //模拟事件处理时间
        TimeUnit.MILLISECONDS.sleep(300);
        log.info("独立消费者{}: {},sequence:{},endOfBatch:{}", name, event.getEventMsgBody(), sequence, endOfBatch);
    }
}
