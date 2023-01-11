package org.jboss.netty.duansg;

import org.jboss.netty.util.HashedWheelTimer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest extends BaseTest {


    public static void main(String[] args) {

        HashedWheelTimer timer = new HashedWheelTimer();
        System.out.println("开始：" + now());
        timer.newTimeout(timeout -> System.err.println("TaskA：" + now()), 3, TimeUnit.SECONDS);
        timer.newTimeout(timeout -> System.err.println("TaskB：" + now()), 5, TimeUnit.SECONDS);
    }


}
