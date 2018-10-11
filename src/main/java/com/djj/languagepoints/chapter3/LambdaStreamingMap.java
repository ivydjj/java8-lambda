package com.djj.languagepoints.chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LambdaStreamingMap {
    public static void main(String[] args) {
        /*
        map: 将一个流中的值转换成一个新的流

        参数和返回值不必属于同一种类型，
        但是Lambda 表达式必须是Function 接口的一个实例，Function 接口是只包含一个参数的普通函数接口。

        接口：java.util.function.Function
        */

        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase()).collect(toList());

        System.out.println(Arrays.asList("A", "B", "HELLO").equals(collected)); // true

        collected = Stream.of("a", "b", "hello")
                .map(String::toUpperCase).collect(toList());

        System.out.println(Arrays.asList("A", "B", "HELLO").equals(collected)); // true
    }
}
