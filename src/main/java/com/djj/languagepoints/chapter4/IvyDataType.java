package com.djj.languagepoints.chapter4;

import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

public class IvyDataType { // 基本类型
    /*
    装箱类型是对象，因此在内存中存在额外开销;整型在内存中占用
    4 字节，整型对象却要占用 16 字节。这一情况在数组上更加严重，整型数组中的每个元素
    只占用基本类型的内存，而整型对象数组中，每个元素都是内存中的一个指针，指向 Java
    堆中的某个对象。在最坏的情况下，同样大小的数组， Integer[] 要比 int[] 多占用 6 倍
    内存。

    将基本类型转换为装箱类型，称为装箱，反之则称为拆箱，两者都需要额外的计算开销。
    对于需要大量数值运算的算法来说，装箱和拆箱的计算开销，以及装箱类型占用的额外内
    存，会明显减缓程序的运行速度。

     Stream 类的某些方法对基本类型和装箱类型做了区分，
    在 Java 8 中，仅对整型、长整型和双浮点型做了特殊处理，因为它们在数值计算中用得最多，特殊处理后的系统性能提升效果最明显。

    对基本类型做特殊处理的方法在命名上有明确的规范：
    如果方法返回类型为基本类型，则在基本类型前加 To ，如ToLongFunction
    如果参数是基本类型，则不加前缀只需类型名即可，如 LongFunction
    如果高阶函数使用基本类型，则在操作后加后缀 To 再加基本类型，如 mapToLong

    如有可能，应尽可能多地使用对基本类型做过特殊处理的方法，进而改善性能，
    这些特殊的 Stream 还提供额外的方法，避免重复实现一些通用的方法，让代码更能体现出数值计算的意图。
     */

    public static void main(String[] args) {
        IntSummaryStatistics statistics = Stream.of("10", "90", "200").mapToInt(Integer::valueOf).summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                statistics.getMax(),
                statistics.getMin(),
                statistics.getAverage(),
                statistics.getSum());
    }

}
