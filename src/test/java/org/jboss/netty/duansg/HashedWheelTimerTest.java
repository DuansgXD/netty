package org.jboss.netty.duansg;

import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.TimerTask;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {


    public static void main(String[] args) {

        HashedWheelTimer timer = new HashedWheelTimer();
        System.out.println("开始：" + now());
        timer.newTimeout(timeout -> System.err.println(now()), 3, TimeUnit.SECONDS);
        timer.newTimeout(timeout -> System.err.println(now()), 5, TimeUnit.SECONDS);

    }

    private static String now() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now());
    }
}
