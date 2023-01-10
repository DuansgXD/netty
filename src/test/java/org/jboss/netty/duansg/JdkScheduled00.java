package org.jboss.netty.duansg;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Duansg
 * @date 2022-12-24 22:39
 */
@SuppressWarnings("all")
public class JdkScheduled00 {

    public static void main(String[] args) {
        // 1、相对延迟或者周期作为定时任务调度，缺点没有绝对的日期或者时间
        // 2、DelayedWorkQueue 是一个无界阻塞队列
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5);
        // 周期执行：时间等于或超过initialDelay首次执行task，之后每隔period重复执行task
        executor.scheduleAtFixedRate(new Task(), 3, 3, TimeUnit.SECONDS);

    }

    static class Task implements Runnable {

        static {
            System.err.println("开始：" +
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now())
            );
        }

        @Override
        public void run() {

            System.err.println("结束：" +
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now())
            );
        }
    }
}
