package com.djj.languagepoints.chapter7;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Lambda表达式的单元测试

Lambda 表达式没有名字，无法直接在测试代码中调用

如果想要对复杂一点的 Lambda 表达式编写单元测试，将其抽取成一个常规的方法。

 */
public class LambdaTest {
    public static void main(String[] args) {
        /*
        第一种是将 Lambda 表达式放入一个方法测试，这种方式要测那个方法，而不是 Lambda 表达式本身。
         */

        // 测试大写转换
        List<String> result = Arrays.asList("a", "b", "hello").stream()
                .map(string -> string.toUpperCase())
                .collect(Collectors.<String>toList());

        System.out.println(result);
        Assert.assertEquals(Arrays.asList("A", "B", "HELLO"), result);

        /*
        如何测试代码，同时享有Lambda 表达式带来的便利？
        请用方法引用。任何 Lambda 表达式都能被改写为普通方法，然后使用方法引用直接引用。
         */
        // 将列表中元素的第一个字母转换成大写

        // 测试字符串包含两个字符的情况，第一个字母被转换为大写
        List<String> input = Arrays.asList("ab");
        List<String> result1 = elementFirstToUpperCaseLambdas(input);
        Assert.assertEquals(Arrays.asList("Ab"), result1);

        // 新版测试
        String input2 = "ab";
        String result2 = firstToUppercase(input2);
        Assert.assertEquals("Ab", result2);
    }


    // 将列表中元素的第一个字母转换成大写
    public static List<String> elementFirstToUpperCaseLambdas(List<String> words) {
        return words.stream()
                .map(value -> {
                    char firstChar = Character.toUpperCase(value.charAt(0));
                    return firstChar + value.substring(1);
                })
                .collect(Collectors.<String>toList());
    }

    // 将首字母转换为大写，应用到所有列表元素
    public static List<String> elementFirstToUppercase(List<String> words) {
        return words.stream()
                .map(LambdaTest::firstToUppercase) // 方法引用
                .collect(Collectors.<String>toList());
    }
    public static String firstToUppercase(String value) {
        char firstChar = Character.toUpperCase(value.charAt(0));
        return firstChar + value.substring(1);
    }
}
