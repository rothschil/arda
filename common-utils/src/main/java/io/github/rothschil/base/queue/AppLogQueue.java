package io.github.rothschil.base.queue;

import io.github.rothschil.base.queue.handler.IQueueTaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.github.rothschil.common.utils.thread.ThreadPoolsUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步处理日志的队列，将需要存储的日志放入这个队列中
 *
 * @author WCNGS@QQ.COM
 * @date 20/11/13 16:14
 * @since 1.0.0
 */
@SuppressWarnings("unused")
@Component
public class AppLogQueue {

    private static final Logger LOG = LoggerFactory.getLogger(AppLogQueue.class);

    /**
     * 定义有界队列，用于存储待处理的实例对象，默认定义的队列上限是 <b>500</b>
     */
    private final LinkedBlockingQueue<IQueueTaskHandler> queue = new LinkedBlockingQueue<>(500);

    /**
     * 检查服务是否运行
     */
    private volatile boolean running = true;

    private final ThreadPoolExecutor threadPoolExecutor = getThread();

    /**
     * 线程状态
     */
    private Future<?> threadStatus = null;

    /**
     * 上下文加载之前处理，且只被执行一次
     *
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @date 2021/10/14-22:24
     **/
    @PostConstruct
    public void init() {
        threadStatus = threadPoolExecutor.submit(() -> {
            while (running) {
                try {
                    // 队列中不存在元素 则不处理
                    if (!queue.isEmpty()) {
                        IQueueTaskHandler taskHandler = queue.take();
                        taskHandler.processData();
                    }
                } catch (InterruptedException e) {
                    LOG.error("服务停止，退出", e);
                    running = false;
                }
            }
        });
    }


    /**
     * 在对象被销毁之前的处理逻辑
     *
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @date 2021/10/14-22:21
     **/
    @PreDestroy
    public void destroys() {

        running = false;
        threadPoolExecutor.shutdownNow();
    }

    /**
     * 当线程池被关闭后，重启初始化线程池服务
     *
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @date 2021/10/14-22:21
     **/
    public void activeService() {
        running = true;
        if (threadPoolExecutor.isShutdown()) {
            init();
            LOG.info("线程池关闭，重新初始化线程池及任务");
        }
        if (threadStatus.isDone()) {
            init();
            LOG.info("线程池任务结束!");
        }
    }

    /**
     * 加入队列
     *
     * @param taskHandler 处理队列中实例
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @date 2021/10/14-22:21
     **/
    public void addQueue(IQueueTaskHandler taskHandler) {
        if (!running) {
            LOG.warn("service is stop");
            return;
        }

        boolean isFull = queue.offer(taskHandler);
        if (!isFull) {
            LOG.warn("添加任务到队列失败");
        }
    }

    /**
     * 判断队列中的实例是否为空
     *
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @date 2021/10/14-22:21
     **/
    public boolean empty() {
        return queue.isEmpty();
    }

    /**
     * 获取定义的线程池
     *
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @date 2021/10/14-22:21
     **/
    private ThreadPoolExecutor getThread() {
        return ThreadPoolsUtil.doCreate(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), this.getClass().getName());
    }

}
