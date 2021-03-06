package com.djj.languagepoints.chapter4;

/**
 * 【@FunctionalInterface】
 * @FunctionalInterface : 用作函数接口的接口都应该添加这个注释
 *
 * 该注释会强制 javac 检查一个接口是否符合函数接口的标准。
 * 如果该注释添加给一个枚举类型、类或另一个注释，或者接口包含不止一个抽象方法， javac 就会报错。
 * 重构代码时，使用它能很容易发现问题。
 *
 *  Java 中有一些接口，虽然只含一个方法，但并不是为了使用Lambda 表达式来实现的:
 *  有些对象内部可能保存着某种状态，使用带有一个方法的接口可能纯属巧合。 java.lang.Comparable 和 java.io.Closeable 就属于这样的情况。
 *
 *  为了提高 Stream 对象可操作性而引入的各种新接口，都需要有 Lambda 表达式可以实现它。
 *  它们存在的意义在于将代码块作为数据打包起来。因此，它们都添加了 @FunctionalInterface 注释。
 *
 *  【权衡】
 *  接口和抽象类之间还是存在明显的区别。
 *  接口允许多重继承，却没有成员变量；
 *  抽象类可以继承成员变量，却不能多重继承。
 *  在对问题域建模时，需要根据具体情况进行权衡，而在以前的 Java 中可能并不需要这样。
 */