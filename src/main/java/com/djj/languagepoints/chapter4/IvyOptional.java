package com.djj.languagepoints.chapter4;

import java.util.Optional;

/*
Optional 是为核心类库新设计的一个数据类型，用来替换 null 值

人们常常使用 null 值表示值不存在， Optional 对象能更好地表达这个概念。使用 null 代
表值不存在的最大问题在于 NullPointerException 。一旦引用一个存储 null 值的变量，程
序会立即崩溃。使用 Optional 对象有两个目的：首先， Optional 对象鼓励程序员适时检查
变量是否为空，以避免代码缺陷；其次，它将一个类的 API 中可能为空的值文档化，这比
阅读实现代码要简单很多。

使用 Optional 对象的方式之一是在调用 get() 方法前，先使用 isPresent 检查 Optional
对象是否有值。使用 orElse 方法则更简洁，当 Optional 对象为空时，该方法提供了一个
备选值。如果计算备选值在计算上太过繁琐，即可使用 orElseGet 方法。该方法接受一个
Supplier 对象，只有在 Optional 对象真正为空时才会调用。

当试图避免空值相关的缺陷，如未捕获的异常时，可以考虑一下是否可使用 Optional对象。
 */
public class IvyOptional {
    public static void main(String[] args) {
        Optional<String> a = Optional.of("a");
        System.out.println(a.get());

        // 创建一个空的 Optional 对象，并检查其是否有值
        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);
        System.out.println(emptyOptional.isPresent()); // false
        System.out.println(alsoEmpty.isPresent()); // false
        System.out.println(a.isPresent()); // true

        System.out.println(emptyOptional.orElse("orElse...")); // orElse...
        System.out.println(emptyOptional.orElseGet(() -> "orElseGet")); // orElseGet
    }
}
