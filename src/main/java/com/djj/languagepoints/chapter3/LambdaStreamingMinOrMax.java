package com.djj.languagepoints.chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaStreamingMinOrMax {
    public static void main(String[] args) {
        /*
        查找Stream 中的最大或最小元素，首先要考虑的是用什么作为排序的指标。

        Java 8 提供了一个新的静态方法comparing，使用它可以方便地实现一个比较器。实际上这个方法接受一个函数并返回另一个函数。
        */

        List<Person> personList = Arrays.asList(new Person("ivy", 10), new Person("juan", 20), new Person("djj", 9));
        Person personMin = personList.stream().min(Comparator.comparing(e -> e.getAge())).get();
        System.out.println("djj".equals(personMin.getName()));
        personMin = personList.stream().min(Comparator.comparing(Person::getAge)).get();
        System.out.println("djj".equals(personMin.getName()));

        Person personMax = personList.stream().max(Comparator.comparing(e -> e.getAge())).get();
        System.out.println("juan".equals(personMax.getName()));
        personMax = personList.stream().max(Comparator.comparing(Person::getAge)).get();
        System.out.println("juan".equals(personMax.getName()));

        // 使用for 循环查找：min
        Person personResult = personList.get(0);
        for (Person person : personList) {
            if (personResult.getAge() > person.getAge()){
                personResult = person;
            }
        }
        System.out.println("djj".equals(personResult.getName()));

        /*
            通用模式：reduce 模式
            
            Object accumulator = initialValue;
            for(Object element : collection) {
            accumulator = combine(accumulator, element);
            }
            首先赋给accumulator 一个初始值：initialValue，然后在循环体中，通过调用combine 函
            数，拿accumulator 和集合中的每一个元素做运算，再将运算结果赋给accumulator，最后
            accumulator 的值就是想要的结果。
            这个模式中的两个可变项是initialValue 初始值和combine 函数。
         */
    }
}

class Person {
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}