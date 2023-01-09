package org.jboss.netty.duansg;

/**
 * @author Duansg
 * @date 2023-01-09 02:18
 */
public class Test {


    public static void main(String[] args) {

//        System.out.println( (1073741824 * 2) -1);
//        System.out.println(Integer.MAX_VALUE);
        new Test().normalizeTicksPerWheel(1073741825);
    }

    private static int normalizeTicksPerWheel(int ticksPerWheel) {
        int normalizedTicksPerWheel = 1;
        while (normalizedTicksPerWheel < ticksPerWheel) {
            normalizedTicksPerWheel <<= 1;
            System.err.println(normalizedTicksPerWheel);
        }
        return normalizedTicksPerWheel;
    }

}
