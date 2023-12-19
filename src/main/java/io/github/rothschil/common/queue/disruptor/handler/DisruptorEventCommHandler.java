package io.github.rothschil.common.queue.disruptor.handler;

import com.lmax.disruptor.WorkHandler;
import io.github.rothschil.common.queue.disruptor.event.DisruptorEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 共同消费者
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
@Slf4j
public class DisruptorEventCommHandler implements WorkHandler<DisruptorEvent> {
    private String name;

    public DisruptorEventCommHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(DisruptorEvent disruptorEvent) throws Exception {
        //模拟事件处理时间
        TimeUnit.MILLISECONDS.sleep(300);
        log.info("共同消费者{} :{}", name, disruptorEvent.getEventMsgBody());
    }
}
