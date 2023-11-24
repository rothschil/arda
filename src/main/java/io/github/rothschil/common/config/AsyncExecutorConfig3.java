// package io.github.rothschil.common.config;
//
// import com.alibaba.ttl.threadpool.TtlExecutors;
// import io.github.rothschil.common.utils.thread.CustomThreadPoolTaskExecutor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.annotation.EnableAsync;
//
// import java.util.concurrent.Executor;
// import java.util.concurrent.ThreadPoolExecutor;
//
// /**
//  * Alibaba线程池
//  *
//  * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
//  * @version 1.0.0
//  */
// @Configuration
// @EnableAsync
// public class AsyncExecutorConfig {
//
//
//     private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
//     private static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 4 < 256 ? 256 : CORE_POOL_SIZE * 4;
//     // 允许线程空闲时间（单位为秒）
//     private static final int KEEP_ALIVE_TIME = 10;
//     // 缓冲队列数
//     private static final int QUEUE_CAPACITY = 4000;
//     // 线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁
//     private static final int AWAIT_TERMINATION = 15;
//     // 用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
//     private static final Boolean WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN = true;
//     // 线程池名前缀
//     private static final String THREAD_NAME_PREFIX = "async-service-";
//
//
//     @Bean("ttlExecutor")
//     public Executor getAsyncExecutor() {
//         CustomThreadPoolTaskExecutor executor = new CustomThreadPoolTaskExecutor();
//         executor.setCorePoolSize(CORE_POOL_SIZE);
//         executor.setMaxPoolSize(MAX_POOL_SIZE);
//         executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
//         executor.setQueueCapacity(QUEUE_CAPACITY);
//         executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
//         executor.setAwaitTerminationSeconds(AWAIT_TERMINATION);
//         executor.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN);
//         // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//         // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
//         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//         executor.initialize();
//         return TtlExecutors.getTtlExecutor(executor);
//     }
//
// }
