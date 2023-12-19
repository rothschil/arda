package io.github.rothschil.common.queue.disruptor.component.impl;

import io.github.rothschil.common.queue.disruptor.component.AbstractDisruptorComponent;
import io.github.rothschil.common.queue.disruptor.handler.ConsumerEventHandlerOne;
import io.github.rothschil.common.queue.disruptor.handler.ConsumerEventHandlerTwo;

public class DisruptorIndComponent extends AbstractDisruptorComponent {
    @Override
    protected void handleEvents() {
        /**
         * 调用handleEventsWith，表示创建的多个消费者，每个都是独立消费的
         * 可以定义不同的消费者处理器，也可使用相同的处理器。
         * 实际场景中应该多数使用不同的处理器，因为正常来讲独立消费者做的应该是不同的事。
         * 所以本例中是定义了两个不同的消费者DisruptorEventIndHandler0和DisruptorEventIndHandler1
         */
        disruptor.handleEventsWith(new ConsumerEventHandlerOne("A"), new ConsumerEventHandlerTwo("B"));
    }
}
