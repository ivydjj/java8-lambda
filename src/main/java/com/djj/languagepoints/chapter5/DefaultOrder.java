package com.djj.languagepoints.chapter5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/*
元素顺序

一些操作在有序的流上开销更大，调用 unordered 方法消除这种顺序就能解决该问题。
大多数操作都是在有序流上效率更高，比如 filter 、 map 和 reduce 等
 */
public class DefaultOrder {
    public static void main(String[] args) {
        System.out.println("-------------------------------------");
        // 在一个有序集合中创建一个流时，流中的元素就按出现顺序排列
        List<Integer> data = Arrays.asList(1, 2, 3, 4);
        List<Integer> sameOrder = data.stream().collect(toList());
        for (int i = 0; i < data.size(); i++){
            System.out.println(data.get(i).equals(sameOrder.get(i))); // 等
        }
        System.out.println("-------------------------------------");
        // 如果集合本身就是无序的，由此生成的流也是无序的。
        Set<Integer> numbers1 = new HashSet<>(data);
        List<Integer> sameOrder1 = numbers1.stream().collect(toList());
        for (int i = 0; i < numbers1.size(); i++){
            System.out.println(data.get(i).equals(sameOrder1.get(i))); // 不一定等
        }
        System.out.println("-------------------------------------");
        // 流的目的不仅是在集合类之间做转换，而且同时提供了一组处理数据的通用操作。有些集合本身是无序的，但这些操作有时会产生顺序
        List<Integer> sameOrder2 = numbers1.stream().sorted().collect(toList());
        for (int i = 0; i < numbers1.size(); i++){
            System.out.println(data.get(i).equals(sameOrder2.get(i))); // 等
        }
        System.out.println("-------------------------------------");
        // 一些中间操作会产生顺序，比如对值做映射时，映射后的值是有序的，这种顺序就会保留下来。如果进来的流是无序的，出去的流也是无序的。
        List<Integer> stillOrdered = data.stream().map(x -> x + 1).collect(toList());
        data = Arrays.asList(2, 3, 4, 5);
        for (int i = 0; i < stillOrdered.size(); i++){
            System.out.println(data.get(i).equals(stillOrdered.get(i))); // 等
        }
        stillOrdered = numbers1.stream().map(x -> x + 1).collect(toList());
        for (int i = 0; i < stillOrdered.size(); i++){
            System.out.println(data.get(i).equals(stillOrdered.get(i))); // 不一定等
        }
    }
}
