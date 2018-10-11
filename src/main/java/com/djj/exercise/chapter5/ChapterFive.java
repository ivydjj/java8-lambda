package com.djj.exercise.chapter5;

import com.djj.exercise.chapter5.customcollector.IvyGroupingBy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ChapterFive {
    public static void main(String[] args) {
        /*
        1. 方法引用
        a. 转换大写的 map 方法；
        b. 使用 reduce 实现 count 方法；
        c. 使用 flatMap 连接列表。
         */

        System.out.println(Stream.of("a", "b", "hello").map(String::toUpperCase).collect(toList()));
        System.out.println(Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element));
        System.out.println(Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(List::stream).collect(Collectors.toList()));

        /*
        2. 收集器

        a. 找出名字最长的艺术家，分别使用收集器和第 3 章介绍过的 reduce 高阶函数实现。然后对比二者的异同：哪一种方式写起来更简单，哪一种方式读起来更简单？
        以下面的参数为例，该方法的正确返回值为 "Stuart Sutcliffe" ：
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");

        b. 假设一个元素为单词的流，计算每个单词出现的次数。
        假设输入如下，则返回值为一个形如 [John → 3, Paul → 2, George → 1] 的 Map ：
        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");

        c. 用一个定制的收集器实现 Collectors.groupingBy 方法，不需要提供一个下游收集器，只需实现一个最简单的即可。
        别看 JDK 的源码，这是作弊！提示：可从下面这行代码开始：
        public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>>
         */

        // a
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        // 收集器
        System.out.println(names.collect(Collectors.maxBy(Comparator.comparing(String::length))).orElseThrow(RuntimeException::new));
        // reduce 高阶函数实现
        names = Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        System.out.println("---" + names.reduce((acc, str) -> (Comparator.comparing(String::length).compare(acc, str) >= 0) ? acc : str).orElseThrow(RuntimeException::new));


        // b
        names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> result = names.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        result.keySet().forEach(e -> System.out.println(e + "-----" + result.get(e)));

        // c custom collector
        names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        System.out.println("---" + names.collect(new IvyGroupingBy(e -> e)));

        /*
        3. 改进Map
        使用 Map 的 computeIfAbsent 方法高效计算斐波那契数列。
        这里的“高效”是指避免将那些较小的序列重复计算多次。

        F(1) = 1
        F(2) = 1
        F(n) = F(n-1) + F(n - 2) n>=3 n为正整数
         */

        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibonacci(2));
        System.out.println(fibonacci.fibonacci(3));

    }

}

class Fibonacci {
    private final Map<Integer,Long> cache;

    public Fibonacci() {
        cache = new HashMap<>();
        cache.put(0, 1L);
        cache.put(1, 1L);
        cache.put(2, 1L);
    }

    public long fibonacci(int x) {
        System.out.println("------------------" + x);
        return cache.computeIfAbsent(x, n -> {
            System.out.println("------------------entry function----");
            return fibonacci(n-1) + fibonacci(n-2);
        });
    }

}