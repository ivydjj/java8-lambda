package com.djj.languagepoints.chapter8.solidrule.singleresponsibility;

import java.util.stream.IntStream;

/**
 * Created by djj on 2018/10/17.
 * 计算质数个数
 *
 */
public class SingleResponsibility {

    /*
    一个方法里塞进了多重职责

    同时干了两件事：计数和判断一个数是否是质数
     */
    public long countPrimes(int upTo) {
        long tally = 0;
        for (int i = 1; i < upTo; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                tally++;
            }
        }
        return tally;
    }


    ////////////////////////////////////////////////
    /*
    中间版

    代码还是有两个功能。代码中的大部分都在对数字循环，如果我们遵守单一功能原则，那么迭代过程应该封装起来。
    改进代码还有一个现实的原因，如果需要对一个很大的 upTo 计数，我们希望可以并行操作。没错，线程模型也是代码的职责之一
     */
    public long countPrimesForTwo(int upTo) {
        long tally = 0;
        for (int i = 1; i < upTo; i++) {
            if (isPrimeForTwo(i)) {
                tally++;
            }
        }
        return tally;
    }
    private boolean isPrimeForTwo(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    ////////////////////////////////////////////////
    /*
    lambda:

    线程模型 并行操作
     */
    public long countPrimesForLambda(int upTo) {
        // 想利用更多 CPU 加速计数操作，可使用 parallelStream 方法
        return IntStream.range(1, upTo)
                .filter(this::isPrimeForLambda)
                .count();
    }

    public long countPrimesForLambdaParallel(int upTo) {
        return IntStream.range(1, upTo)
                .parallel()
                .filter(this::isPrimeForLambda)
                .count();
    }

    private boolean isPrimeForLambda(int number) {
        return IntStream.range(2, number)
                .allMatch(x -> (number % x) != 0);
    }
}
