package com.djj.languagepoints.chapter5;

/*
方法引用：
artist -> artist.getName() 转为 Artist::getName

【标准语法为 Classname::methodName】
需要注意的是，虽然这是一个方法，但不需要在后面加括号，因为这里并不调用该方法。
我们只是提供了和 Lambda 表达式等价的一种结构，在需要时才会调用。
凡是使用 Lambda 表达式的地方，就可以使用方法引用。

【构造函数也有同样的缩写形式】
(name, nationality) -> new Artist(name, nationality) 转为 Artist::new

【创建数组】
String[]::new

【注意】
方法引用自动支持多个参数，前提是选对了正确的函数接口。
每次写出形如 x -> foo(x) 的 Lambda 表达式时，和直接调用方法 foo 是一样的。方法引用只不过是基于这样的事实，提供了一种简短的语法而已。
 */
public class MethodReference {
    public static void main(String[] args) {

    }
}
