package io.github.rothschil.common.queue.disruptor.event;

/**
 *
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
import lombok.Data;

import java.io.Serializable;

/**
 *  时间消息内容体
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
@Data
public class DisruptorEvent implements Serializable {

    /**
     * 消息内容，此处定义为一个字符类
     */
    private String eventMsgBody;
}
