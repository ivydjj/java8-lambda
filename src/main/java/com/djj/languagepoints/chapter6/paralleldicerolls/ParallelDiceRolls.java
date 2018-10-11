package com.djj.languagepoints.chapter6.paralleldicerolls;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// lambda 方式模拟掷骰子事件：计算获取点数的概率
public class ParallelDiceRolls {
    private static final int N = 100000000;
    private final double fraction = 1.0 / N; // 基本概率

    public static void main(String[] args) {
        Map<Integer, Double> result = new ParallelDiceRolls().parallelDiceRolls();
        result.keySet().forEach(e -> System.out.println(e + "----------" + result.get(e)));
    }

    public Map<Integer, Double> parallelDiceRolls() {
        return IntStream.range(0, N) // 使用 IntStream 的 range 方法创建大小为 N 的流
                 .parallel() // 调用 parallel 方法使用流的并行化操作
                .mapToObj(twoDiceThrows()) // twoDiceThrows 函数模拟了连续掷两次骰子事件，返回值是两次点数之和,使用 mapToObj 方法以便在流上使用该函数
                .collect(Collectors.groupingBy(side -> side, // 得到了需要合并的所有结果的流
                Collectors.summingDouble(n -> fraction)));
    }

    private IntFunction<Integer> twoDiceThrows() { // 抛骰子
        return (value) -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int firstThrow = random.nextInt(1, 7); // 1-6
            int secondThrow = random.nextInt(1, 7); // 1-6
            return firstThrow + secondThrow;
        };
    }
}
