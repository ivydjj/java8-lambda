package com.djj.languagepoints.chapter6.paralleldicerolls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

/*
手动使用线程模拟掷骰子事件：计算获取点数的概率
 */
public class ManualDiceRolls {
    private static final int N = 100000000;

    private final double fraction; // 基本概率
    private final Map<Integer, Double> results; // 结果概率
    private final int numberOfThreads; // jvm的cpu数
    private final ExecutorService executor; // 线程服务
    private final int workPerThread; // 每个线程中work数

    public static void main(String[] args) {
        ManualDiceRolls roles = new ManualDiceRolls();
        roles.simulateDiceRoles();
    }

    public ManualDiceRolls() { // 数据初始化
        fraction = 1.0 / N;

        results = new ConcurrentHashMap<>(); // ConcurrentHashMap 线程安全
        numberOfThreads = Runtime.getRuntime().availableProcessors(); // 可用的cpu数
        System.out.println("-----numberOfThreads---------" + numberOfThreads);
        executor = Executors.newFixedThreadPool(numberOfThreads);
        workPerThread = N / numberOfThreads;
    }

    public void simulateDiceRoles() { // 模拟掷骰子
        List<Future<?>> futures = submitJobs();
        awaitCompletion(futures);
        printResults();
    }

    private void printResults() { // 打印结果
        results.entrySet()
                .forEach(System.out::println);
    }

    private List<Future<?>> submitJobs() { // 提交
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(executor.submit(makeJob()));
        }
        return futures;
    }

    private Runnable makeJob() { // 执行
        return () -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < workPerThread; i++) {
                int entry = twoDiceThrows(random);
                accumulateResult(entry);
            }
        };
    }

    private void accumulateResult(int entry) { // 出现一次，累加一次概率
        results.compute(entry, (key, previous) -> previous == null ? fraction : previous + fraction);
    }

    private int twoDiceThrows(ThreadLocalRandom random) { // 抛骰子
        int firstThrow = random.nextInt(1, 7); // 1-6
        int secondThrow = random.nextInt(1, 7); // 1-6
        return firstThrow + secondThrow;
    }

    private void awaitCompletion(List<Future<?>> futures) { // 等待完成
        futures.forEach((future) -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }

}
