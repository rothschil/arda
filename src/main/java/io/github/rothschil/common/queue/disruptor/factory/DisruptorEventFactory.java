package io.github.rothschil.common.queue.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import io.github.rothschil.common.queue.disruptor.event.DisruptorEvent;

/**
 *
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
public class DisruptorEventFactory implements EventFactory<DisruptorEvent> {
    @Override
    public DisruptorEvent newInstance() {
        return new DisruptorEvent();
    }
}
