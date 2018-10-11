package com.djj.exercise.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ChapterSix {
    public static void main(String[] args) {
        /*
        1. 例 6-10 中的代码顺序求流中元素的平方和，将其改为并行处理。

         */
        System.out.println(sequentialSumOfSquares(IntStream.range(1, 10)));
        System.out.println(sequentialSumOfSquares2(IntStream.range(1, 10)));

        /*
        2. 例 6-11 中的代码把列表中的数字相乘，然后再将所得结果乘以 5。顺序执行这段程序没
        有问题，但并行执行时有一个缺陷，使用流并行化执行该段代码，并修复缺陷。
         */
        System.out.println(multiplyThrough(Arrays.asList(1, 2, 3, 4, 5, 6)));
        System.out.println(multiplyThrough2(Arrays.asList(1, 2, 3, 4, 5, 6)));

        /*
        3. 例 6-12 中的代码计算列表中数字的平方和。尝试改进代码性能，但不得牺牲代码质量。
        只需要一些简单的改动即可。
         */
        System.out.println(slowSumOfSquares(Arrays.asList(1, 2, 3, 4, 5, 6)));
        System.out.println(slowSumOfSquares2(Arrays.asList(1, 2, 3, 4, 5, 6)));

    }

    // 例 6-12　求列表元素的平方和，该实现方式性能不高
    public static int slowSumOfSquares(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .map(x -> x * x)
                .reduce(0, (acc, x) -> acc + x);
    }

   public static int slowSumOfSquares2(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .mapToInt(x -> x * x)
                .sum();
    }


    // 例 6-11　把列表中的数字相乘，然后再将所得结果乘以 5，该实现有一个缺陷
    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.stream()
                .reduce(5, (acc, x) -> x * acc);
    }

   public static int multiplyThrough2(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .reduce(1, (acc, x) -> x * acc) * 5;
    }

    // 例 6-10　顺序求列表中数字的平方和
    public static int sequentialSumOfSquares(IntStream range) {
        return range.map(x -> x * x)
                .sum();
    }

   public static int sequentialSumOfSquares2(IntStream range) {
        return range.parallel().map(x -> x * x)
                .sum();
    }

}
