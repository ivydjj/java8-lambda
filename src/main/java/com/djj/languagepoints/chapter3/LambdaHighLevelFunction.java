package com.djj.languagepoints.chapter3;

// 高阶函数
public class LambdaHighLevelFunction {
    public static void main(String[] args) {
        /*
        高阶函数是指接受另外一个函数作为参数，或返回一个函数的函数。
        高阶函数不难辨认：看函数签名就够了。如果函数的参数列表里包含函数接口，或该函数返回一个函数接口，那么该函数就是高阶函数。

        【Comparator】
        Comparator 可能会被误认为是一个对象，但它有且只有一个抽象方法，所以实际上是一个函数接口。
        Comparator 实际上应该是个函数，但是那时的Java 只有对象，因此才造出了一个类，一个匿名类。成为对象实属巧合

        【副作用】
        没有副作用的函数不会改变程序或外界的状态
        使用 Lambda 表达式获取值而不是变量。获取值使用户更容易写出没有副作用的代码。
        无论何时，将 Lambda 表达式传给 Stream 上的高阶函数，都应该尽量避免副作用。唯一的例外是 forEach 方法，它是一个终结方法。

        在 Lambda 表达式中使用局部变量，可以不使用 final 关键字，但局部变量在既成事实上必须是 final 的。
        */



    }
}

