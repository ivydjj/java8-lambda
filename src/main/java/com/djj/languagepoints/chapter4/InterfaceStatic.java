package com.djj.languagepoints.chapter4;

import java.util.stream.Stream;

/*
【接口的静态方法】
 Java 8 中添加的一个新的语言特性

类是一个放置工具方法的好地方，比如 Java 7 中引入的 Objects 类，就包含了很多工具方法，这些方法不是具体属于某个类的。

 如果一个方法有充分的语义原因和某个概念相关，那么就应该将该方法和相关的类或接口放在一起，而不是放到另一个工具类中。

 Stream.of
 */
public class InterfaceStatic {
    public static void main(String[] args) {
        Stream.of(1, 3, 5); // static
    }
}
