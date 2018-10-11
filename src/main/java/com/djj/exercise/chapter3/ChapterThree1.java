package com.djj.exercise.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ChapterThree1 {
    public static void main(String[] args) {
        System.out.println(streamFilter(Arrays.asList(2, 3, 8), e -> e > 5));

        System.out.println(streamMap(Arrays.asList(new Book("book1", 100.1), new Book("book2", 200.2)), e  -> e.getPrice()));
    }

    // 只用 reduce 和 Lambda 表达式写出实现 Stream 上的 filter操作的代码，如果不想返回Stream ，可以返回一个 List 。
    public static <I> List<I> streamFilter(List<I> objList, Predicate<I> predicate) {
        List<I> inital = new ArrayList<I>();
        return objList.stream().reduce(inital,
                (List<I> acc, I obj) -> {
                    if (predicate.test(obj)) {
                        List<I> newAcc = new ArrayList<I>(acc);
                        newAcc.add(obj);
                        return newAcc;
                    } else {
                        return acc;
                    }
                }, ChapterThree1::streamCombiner);

    }

    public static <I> List<I> streamCombiner(List<I> left, List<I> right){
        List<I> newList = new ArrayList<>(left);
        newList.addAll(right);
        return newList;
    }

    ///////////////////////////

    // 只用 reduce 和 Lambda 表达式写出实现 Stream 上的map 操作的代码，如果不想返回Stream ，可以返回一个 List 。
    public static <I, V> List<V> streamMap(List<I> objList, Function<I, V> function){
        return objList.stream().reduce(new ArrayList<V>(0), (List<V> acc, I obj) -> {
            List<V> newAcc = new ArrayList<V>(acc);
            newAcc.add(function.apply(obj));
            return newAcc;
        }, (List<V> left, List<V> right) -> {
            List<V> newList = new ArrayList<>(left);
            newList.addAll(right);
            return newList;
        });
    }

}

