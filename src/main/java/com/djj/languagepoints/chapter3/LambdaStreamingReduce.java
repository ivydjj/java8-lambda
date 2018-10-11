package com.djj.languagepoints.chapter3;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class LambdaStreamingReduce {
    public static void main(String[] args) {
        /*
        reduce：可以实现从一组值中生成一个值
        count、min 和max 方法，因为常用而被纳入标准库中。事实上，这些方法都是reduce 操作。

        reducer的类型是BinaryOperator
        */

        // 求和
        // Lambda 表达式的返回值是最新的acc，是上一轮acc 的值和当前元素相加的结果
        int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
        System.out.println((1 + 2 + 3) == count); // true

        // 上面求和操作中展开reduce 操作
        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element; // java.util.function.BiFunction
        count = accumulator.apply(
                accumulator.apply(
                        accumulator.apply(0, 1),
                        2),
                3);
        System.out.println((1 + 2 + 3) == count); // true

        // 命令式编程
        // 在命令式编程方式下，每一次循环将集合中的元素和累加器相加，用相加后的结果更新累加器的值。
        // 对于集合来说，循环在外部，且需要手动更新变量。
        int acc = 0;
        for (Integer element : Arrays.asList(1, 2, 3)) {
            acc = acc + element;
        }
        System.out.println((1 + 2 + 3) == count); // true

        // Stream 的链式操作：综合操作

        /*
            Stream 工厂：通过Stream 暴露集合的最大优点在于，它很好地封装了内部实现的数据结构。
            仅暴露一个Stream 接口，用户在实际操作中无论如何使用，都不会影响内部的List 或Set。

            任何时候想转化或替代代码，都该使用map 操作
         */

        // 重构

//        // 旧
//        public Set<String> findLongTracks(List<Album> albums) {
//            Set<String> trackNames = new HashSet<>();
//            for(Album album : albums) {
//                for (Track track : album.getTrackList()) {
//                    if (track.getLength() > 60) {
//                        String name = track.getName();
//                        trackNames.add(name);
//                    }
//                }
//            }
//            return trackNames;
//        }
//
//        // 新
//        public Set<String> findLongTracks(List<Album> albums) {
//            return albums.stream()
//                    .flatMap(album -> album.getTracks())
//                    .filter(track -> track.getLength() > 60)
//                    .map(track -> track.getName())
//                    .collect(toSet());
//        }


    }
}

