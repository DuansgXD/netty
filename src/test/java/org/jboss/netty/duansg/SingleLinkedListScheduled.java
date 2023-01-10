package org.jboss.netty.duansg;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author Duansg
 * @date 2022-12-27 23:02
 */
public class SingleLinkedListScheduled {

    public static void main(String[] args) {

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addTask(o -> System.out.println("task3 running:" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now())), 3000, TimeUnit.MILLISECONDS);
        singleLinkedList.addTask(o -> System.err.println("task2 running:" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now())), 2000, TimeUnit.MILLISECONDS);
        singleLinkedList.addTask(o -> System.err.println("task4 running:" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now())), 4000, TimeUnit.MILLISECONDS);

        new Scheduled(singleLinkedList).start();
    }

    @SuppressWarnings("all")
    static class Scheduled {

        private SingleLinkedList singleLinkedList;

        private Thread thread;

        public Scheduled(SingleLinkedList singleLinkedList) {
            this.singleLinkedList = singleLinkedList;
            this.thread = new Thread(() -> running(singleLinkedList));
        }

        private void running(SingleLinkedList singleLinkedList) {
            for (;;) {
                long currentTimeMillis = System.currentTimeMillis();

                Node node = singleLinkedList.getHead();

                if (currentTimeMillis < node.getTime()) {
                    continue;
                }
                singleLinkedList.clearHead();

                singleLinkedList.addTask(node.task, node.period, TimeUnit.MILLISECONDS);

                // run task
                node.getTask().accept(node.getTime());
            }
        }

        public void start() {
            this.thread.start();
        }
    }

    static class SingleLinkedList {

        public Node<Object> head;

        public Node<Object> getHead() {
            return this.head;
        }

        public Node<Object> clearHead() {
            Node<Object> temp = this.head;
            this.head = temp.next;
            return temp;
        }

        public void addTask(Consumer<Object> task, long period, TimeUnit timeUnit) {
            long millis = System.currentTimeMillis() + period;

            // 初始化没有头节点的时候
            if (null == this.head) {
                this.head = new Node<>(millis, period, task);
                return;
            }
            // 当比头节点还小的时候，直接覆盖
            if (millis <= this.head.getTime()) {
                this.head = new Node<>(millis, period, task, this.head);
                return;
            }
            // 以下都是比头节点还大的
            Node<Object> currentNode = this.head;
            for (;;) {
                // 没有后继节点了直接追加
                if (null == currentNode.next) {
                    currentNode.next = new Node<>(millis, period, task);
                    return;
                }
                // 如果比当前节点大，但是比当前的下一个节点小，直接插在这个中间
                if (currentNode.getTime() < millis && millis < currentNode.next.getTime()) {
                    currentNode.next = new Node<>(millis, period, task, currentNode.next);
                    return;
                }
                // 否则继续下一个
                currentNode = currentNode.next;
            }
        }
    }

    static class Node<T> {

        private long time;

        private long period;

        private Consumer<T> task;

        private Node<T> next;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getPeriod() {
            return period;
        }

        public void setPeriod(long period) {
            this.period = period;
        }

        public Consumer<T> getTask() {
            return task;
        }

        public void setTask(Consumer<T> task) {
            this.task = task;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node(long time, long period, Consumer<T> task) {
            this.task = task;
            this.time = time;
            this.period = period;
        }

        public Node(long time, long period, Consumer<T> task, Node<T> next) {
            this.task = task;
            this.time = time;
            this.period = period;
            this.next = next;
        }

    }

}
