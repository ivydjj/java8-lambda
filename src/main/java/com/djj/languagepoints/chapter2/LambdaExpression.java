package com.djj.languagepoints.chapter2;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/*
    【Lambda表达式】
    Lambda 表达式都是静态类型
    Lambda 表达式是一个匿名方法，将行为像数据一样进行传递。

    【正确使用Lambda表达式】
    明确了要达成什么转化，而不是说明如何转化:
    一方面，潜在的缺陷更少，更直接地表达了程序员的意图；
    另一方面在于写出的函数没有副作用。这一点非常重要，这样只通过函数的返回值就能充分理解函数的全部作用。

    将Lambda 表达式传给Stream 上的高阶函数，都应该尽量避免副作用。唯一的例外是forEach 方法，它是一个终结方法。
 */
public class LambdaExpression {

    public static void main(String[] args) {
        /////////////////////////////////////// 参数：类型推断
        // 所有Lambda 表达式中的参数类型都是由编译器推断得出的
        // 将Lambda 表达式赋值给一个局部变量，或传递给一个方法作为参数，
        // 局部变量或方法参数的类型就是Lambda 表达式的目标类型

        // 初始化数组时，数组的类型就是根据上下文推断出来的。
        // 另一个常见的例子是null，只有将null赋值给一个变量，才能知道它的类型。
        final String[] array = { "hello", "world" };

        // 类型推断
        //  Java 7 中程序员可省略构造函数的泛型类型，Java 8 更进一步，程序员可省略Lambda 表达式中的所有参数类型。
        // javac 根据Lambda 表达式上下文信息就能推断出参数的正确类型。
        // 程序依然要经过类型检查来保证运行的安全性，但不用再显式声明类型罢了。这就是所谓的类型推断。
        // 类型推断系统相当智能，但若信息不够，类型推断系统也无能为力。类型系统不会漫无边际地瞎猜，而会中止操作并报告编译错误，寻求帮助

        // Java 7 中的菱形操作符(<>)，它可使javac 推断出泛型参数的类型
        // 变量oldWordCounts 明确指定了泛型的类型，
        // 而变量diamondWordCounts则使用了菱形操作符。不用明确声明泛型类型，编译器就可以自己推断出来
        Map<String, Integer> oldWordCounts = new HashMap<String, Integer>();
        Map<String, Integer> diamondWordCounts = new HashMap<>();

        // java7不能编译通过，java8能行
        useHashmap(new HashMap<>());

        // eg.
        // 表达式 x > 5 是Lambda 表达式的主体。这样的情况下，返回值就是Lambda 表达式主体的值。
        Predicate<Integer> atLeast5 = x -> x > 5;

        BinaryOperator<Long> addLongs = (x, y) -> x + y;

        /////////////////////////////////////// 参数：final
        // Lambda 表达式中引用的局部变量必须是final 或既成事实上的final 变量
        String name = "finalName";
        Runnable runnable1 = () -> System.out.println("final: " + name);

//        // 如果你试图给该变量多次赋值，然后在Lambda 表达式中引用它，编译器就会报错。
//        // 显示出错信息：local variables referenced from a Lambda expression must be final or effectively final1。
//        String name1 = "finalName";
//        name1 = "changeName";
//        Runnable runnable2 = () -> System.out.println("final: " + name1);

        // Lambda 表达式都是静态类型
        // Lambda 表达式也被称为闭包：未赋值的变量与周边环境隔离起来，进而被绑定到一个特定的值


        /////////////////////////////////////// 表达式

        // Lambda 表达式不包含参数，使用空括号() 表示没有参数。该Lambda 表达式
        // 实现了Runnable 接口，该接口也只有一个run 方法，没有参数，且返回类型为void
        Runnable noArguments = () -> System.out.println("Hello World");

        // Lambda 表达式包含且只包含一个参数，可省略参数的括号
        ActionListener oneArgument = event -> System.out.println("button clicked");

        // Lambda 表达式的主体不仅可以是一个表达式，而且也可以是一段代码块，使用大括号（{}）将代码块括起来
        // 该代码块和普通方法遵循的规则别无二致，可以用返回或抛出异常来退出。只有一行代码的Lambda 表达式也可使用大括号，
        // 用以明确Lambda表达式从何处开始、到哪里结束。
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };

        // Lambda 表达式也可以表示包含多个参数的方法
        // 这时就有必要思考怎样去阅读该Lambda 表达式。这行代码并不是将两个数字相加，
        // 而是创建了一个函数，用来计算两个数字相加的结果。
        // 变量add 的类型是BinaryOperator<Long>，它不是两个数字的和，而是将两个数字相加的那行代码。
        BinaryOperator<Long> add = (x, y) -> x + y;

        // 可以显式声明参数类型，此时就需要使用小括号将参数括起来，多个参数的情况也是如此
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
    }

    private static void useHashmap(Map<String, String> values){

    }
}
