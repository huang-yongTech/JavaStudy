package com.hy.runnable;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by huangyong on 2017/5/11.
 * 测试多线程fork/join
 */
public class TestForkJoin {
    private static final int THREADSHOLD = 10000;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        NumTask numTask = new NumTask(0, 100000L);

        Long currentTimeMillis = System.currentTimeMillis();
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(numTask);
        Long result = 0L;
        try {
            result = forkJoinTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            System.out.println("最终结果：" + result);
            System.out.println("运行时间：" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    private static class NumTask extends RecursiveTask<Long> {
        private long start;
        private long end;

        NumTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean canCompute = (end - start) < THREADSHOLD;

            if (canCompute) {
                for (long i = start; i < end; i++) {
                    sum += i;
                }
            } else {
                //分成100个小任务
                long step = (end - start) / 100;
                ArrayList<NumTask> subTasks = new ArrayList<>();
                long startPos = start;
                for (long i = 0; i < 100; i++) {
                    long endPos = startPos + step;
                    if (endPos > end)
                        endPos = end;

                    NumTask subTask = new NumTask(startPos, endPos);
                    startPos = endPos + 1;
                    subTasks.add(subTask);

                    subTask.fork();
                }

                for (NumTask numTask : subTasks) {
                    sum += numTask.join();
                }
            }

            return sum;
        }
    }
}
