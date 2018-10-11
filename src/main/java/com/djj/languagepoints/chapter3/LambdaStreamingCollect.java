package com.djj.languagepoints.chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaStreamingCollect {
    public static void main(String[] args) {
        // collect(toList()) 方法由Stream 里的值生成一个列表，是一个及早求值操作
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(Arrays.asList("a", "b", "c").equals(collected)); // true
    }
}
