package org.jboss.netty.duansg;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Duansg
 * @date 2022-12-26 23:54
 */
@SuppressWarnings("all")
public class TimerExample {

    public static void main(String[] args) {
        Timer timer = new Timer();

        // 1、Timer是单线程，且顺序执行提交的任务。
        // 2、某一个任务发生了异常，剩下的任务将不会再执行。
        // 3、某一个任务执行超过设置的周期时间间隔，下一个任务将延迟执行。
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int a = 1/0;
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task1-结束：" +
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now())
                );
            }
        }, 3000, 3000);
    }
}
