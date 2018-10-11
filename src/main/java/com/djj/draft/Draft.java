package com.djj.draft;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Draft {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // 没有满足条件的数据，返回0
        System.out.println(list.stream().filter(e -> e == 5).mapToInt(e -> e).sum());

        IntStream.range(0, 2).forEach(System.out::println);
        IntStream.range(0, 2).forEach(e -> System.out.println(e));
    }
}
