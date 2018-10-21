package com.djj.languagepoints.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
    Stream 是用函数式编程方式在集合类上进行复杂操作的工具

    和 Iterator 类似，Stream 是一种内部迭代方式
    内部迭代将更多控制权交给了集合类


    Stream 是为构建内存中集合的计算流程而设计的

 */
public class LambdaStreaming {
    public static void main(String[] args) {
        List<String> listData = new ArrayList<>(Arrays.asList("book", "computer", "earth", "cup", "box", "paper", "pen"));

        /////////////////////// 惰性求值 & 及早求值
        /*
        判断一个操作是惰性求值还是及早求值很简单：只需看它的返回值。
        如果返回值是Stream，那么是惰性求值；如果返回值是另一个值或为空，那么就是及早求值。
        使用这些操作的理想方式就是形成一个惰性求值的链，最后用一个及早求值的操作返回想要的结果，
        这正是它的合理之处。

        参考【建造者模式】，建造者模式使用一系列操作设置属性和配置，最后调用一个build 方法，这时，对象才被真正创建。

        Streams are lazy and others are Eager
        */
        // 惰性求值
        listData.stream()
                .filter(data -> {
                    System.out.println("filter print--------------" + data);
                    return data.equals("book");
                });

        // 及早求值
        listData.stream()
                .filter(data -> {
                    System.out.println("count print--------------" + data);
                    return data.equals("book");
                }).count();

        /////////////////////// 集合内部迭代
        // 常规循环
        long count = 0;
        for (String data : listData) {
            if (data.equals("book")) {
                count++;
            }
        }
        // 在集合外部迭代
        count = 0;
        Iterator<String> iterator = listData.iterator();
        while(iterator.hasNext()) {
            String data = iterator.next();
            if (data.equals("book")) {
                count++;
            }
        }

        // 在集合内部迭代
        count = listData.stream()
                .filter(data -> data.equals("book"))
                .count();
    }
}
