package com.djj.languagepoints.chapter6;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
并行化数组操作

parallelPrefix 任意给定一个函数，计算数组的和
parallelSetAll 使用 Lambda 表达式更新数组元素
parallelSort 并行化对数组元素排序

改变了传入的数组，而没有创建一个新的数组

 */
public class ParallelArrays {
    public static void main(String[] args) {
//        for (double v : imperativeInitilize(10)) {
//            System.out.print(v);
//        }
//        for (double v : parallelInitialize(10)) {
//            System.out.print(v);
//        }

        double[] d = new double[] {0, 1, 2, 3, 4, 3.5};
        double[] result = simpleMovingAverage(d, 3);
        for (double v : result) {
            System.out.println(v);
        }
    }

    ////////////// parallelPrefix /////////////////////
    // 计算简单滑动平均数
    /*
    一个简单的滑动平均数。在时间序列上增加一个滑动窗口，计算出窗口中的平均值。
    如果输入数据为 0、1、2、3、4、3.5，滑动窗口的大小为 3，则简单滑动平均数为 1、2、3、3.5
     */
    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length); // 复制一份输入数据
        Arrays.parallelPrefix(sums, Double::sum); // 执行并行操作，将数组的元素相加。现在 sums 变量中保存了求和结果
        for (double sum : sums) {
            System.out.println(sum);
        }

        /*
        有了和，就能计算出时间窗口中的和了，减去窗口起始位置的元素即可，除以 n 即得到平均值。
        可以使用已有的流中的方法计算该值，使用 Intstream.range得到包含所需元素下标的流。
         */
        int start = n - 1; // 数组下标从0开始
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = (i == start ? 0 : sums[i - n]);
                    return (sums[i] - prefix) / n; // 使用总和减去窗口起始值，然后再除以 n 得到平均值
                }).toArray(); //将流转换为数组
    }

    ////////////// parallelSetAll /////////////////////

    // 使用 for 循环初始化数组
    public static double[] imperativeInitilize(int size) {
        double[] values = new double[size];
        for(int i = 0; i < values.length;i++) {
            values[i] = i;
        }
        return values;
    }

    // 使用并行化数组操作初始化数组
    public static double[] parallelInitialize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }
}
