package com.djj.languagepoints.chapter3;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LambdaStreamingFlatMap {
    public static void main(String[] args) {
        /*
        flatMap：可用Stream 替换值， 然后将多个Stream 连接成一个Stream

        但是Lambda 表达式必须是Function 接口的一个实例，Function 接口是只包含一个参数的普通函数接口。

        接口：java.util.function.Function
        */

        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());

        System.out.println(Arrays.asList(1, 2, 3, 4).equals(together)); // true

        together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(Collection::stream)
                .collect(toList());

        System.out.println(Arrays.asList(1, 2, 3, 4).equals(together)); // true
    }
}
