package com.djj.languagepoints.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaStreamingFilter {
    public static void main(String[] args) {
        /*
        filter: 遍历数据并检查其中的元素

        filter 接受一个函数作为参数，该函数用Lambda 表达式表示
        由于此方法和if 条件语句的功能相同，因此其返回值肯定是true 或者false。
        经过过滤，Stream 中符合条件的，即Lambda 表达式值为true 的元素被保留下来。

        接口：java.util.function.Predicate
        */

        // 旧版方法
        List<String> beginningWithNumbers = new ArrayList<>();
        for(String value : Arrays.asList("a", "1abc", "abc1")) {
            if (Character.isDigit(value.charAt(0))) {
                beginningWithNumbers.add(value);
            }
        }
        System.out.println(Arrays.asList("1abc").equals(beginningWithNumbers));

        // filter
        beginningWithNumbers
                = Stream.of("a", "1abc", "abc1")
                .filter(value -> Character.isDigit(value.charAt(0)))
                .collect(Collectors.toList());
        System.out.println(Arrays.asList("1abc").equals(beginningWithNumbers));
    }
}
