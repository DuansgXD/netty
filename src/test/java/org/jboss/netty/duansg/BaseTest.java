package org.jboss.netty.duansg;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    protected static String now() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now());
    }
}
