package com.djj.languagepoints.chapter4;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/*
    【Lambda 表达式作为参数时，其类型由它的目标类型推导得出，推导过程遵循如下规则】
    如果只有一个可能的目标类型，由相应函数接口里的参数类型推导得出；
    如果有多个可能的目标类型，由最具体的类型推导得出；
    如果有多个可能的目标类型且最具体的类型不明确，则需人为指定类型。
 */
public class OverloadAnalyze { // 重载解析
    /*
     重载时，javac 会挑出最具体的类型。

     在 Java 中可以重载方法，造成多个方法有相同的方法名，但签名确不一样。
     在推断参数类型时会带来问题，因为系统可能会推断出多种类型。

     BinaryOperator 是一种特殊的 BiFunction 类型，参数的类型和返回值的类型相同。
     Lambda 表达式的类型就是对应的函数接口类型，因此，将 Lambda 表达式作为参数
    传递时，情况也依然如此。操作时可以重载一个方法，分别接受 BinaryOperator 和该
    接口的一个子类作为参数。调用这些方法时，Java 推导出的 Lambda 表达式的类型正
    是最具体的函数接口的类型。

     */

    public static void main(String[] args) {
        overloadedMethod("abc"); // String

        overloadedMethod1((x, y) -> x + y); // IntegerBinaryOperator

        /*
        传入 overloadedMethod 方法的 Lambda 表达式和两个函数接口 Predicate 、 IntPredicate 在
        类型上都是匹配的。在这段代码块中，两种情况都定义了相应的重载方法，这时， javac
        就无法编译，在错误报告中显示 Lambda 表达式被模糊调用。 IntPredicate 没有继承
        Predicate ，因此编译器无法推断出哪个类型更具体。
         */
//        overloadedMethod3((x) -> true); // 报错
    }

    private static void overloadedMethod(Object o) {
        System.out.println("Object");
    }
    private static void overloadedMethod(String s) { // String 跟具体
        System.out.println("String");
    }

    private static void overloadedMethod1(BinaryOperator<Integer> Lambda) {
        System.out.println("BinaryOperator");
    }
    private static void overloadedMethod1(IntegerBiFunction Lambda) {
        System.out.println("IntegerBinaryOperator");
    }

    private void overloadedMethod3(Predicate<Integer> predicate) {
        System.out.print("Predicate");
    }
    private void overloadedMethod3(IntPredicate predicate) {
        System.out.print("IntPredicate");
    }
}

interface IntegerBiFunction extends BinaryOperator<Integer> { // IntegerBiFunction 更具体
}

interface IntPredicate {
    public boolean test(int value);
}


