package com.djj.languagepoints.chapter5.customcollect;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
重构和定制收集器

说明：
对收集器的归一化处理，使用 reducing 收集器，它为流上的归一操作提供了统一实现


 */
public class CustomCollect {
    public static void main(String[] args) {
        // 自定义收集器
        System.out.println(Stream.of("iii", "jjjj", "kkk").collect(new StringCollector("(", ")", ",")));

        // 对收集器的归一化处理
        String result =
                Stream.of("iii", "jjjj", "kkk")
                        .collect(Collectors.reducing(
                                new StringCombiner("[", "]", ","),
                                name -> new StringCombiner("[", "]", ",").add(name),
                                StringCombiner::merge))
                        .toString();
        System.out.println(result);
    }
}
