package com.hy.runnable;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by huangyong on 2017/5/11.
 * 测试多线程fork/join
 */
public class TestForkJoin {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        NumTask numTask = new NumTask(0, 100000L);
        Task task = new Task(0, 100000L);

        System.out.println("----采用fork/join执行任务----");
        long currentTime = System.currentTimeMillis();
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(numTask);
        try {
            long result = forkJoinTask.get();
            System.out.println("最终结果：" + result);
            System.out.println("运行时间：" + (System.currentTimeMillis() - currentTime));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("----采用普通结果执行任务----");
        long currentTime1 = System.currentTimeMillis();

        long result1 = task.run();
        System.out.println("最终结果：" + result1);
        System.out.println("运行时间：" + (System.currentTimeMillis() - currentTime1));
    }

    private static class NumTask extends RecursiveTask<Long> {
        private static final int THREADSHOLD = 10000;
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
                //分成若干个小任务
                long step = ( end - start) / 100;
                ArrayList<NumTask> subTasks = new ArrayList<>();
                long startPos = start;
                for (long i = 0; i < 100; i++) {
                    long endPos = startPos + step;
                    if (endPos > end)
                        endPos = end;

                    NumTask subTask = new NumTask(startPos, endPos);
                    startPos += step + 1;
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

    private static class Task {
        private long start;
        private long end;

        Task(long start, long end) {
            this.start = start;
            this.end = end;
        }

        Long run() {
            long sum = 0;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
