package com.djj.exercise.chapter2;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chapter2 {
    public static void main(String[] args) {
        // 1
        Function<Integer, Integer> add = i -> i + 1;

        // synchorinized & ThreadLocal
        // 有时候一个对象的变量会被多个线程所访问，这时就会有线程安全问题，当然我们可以使用 synchorinized 关键字来为此变量加锁，进行同步处理，
        // 从而限制只能有一个线程来使用此变量，但是加锁会大大影响程序执行效率。
        // 当使用 ThreadLocal 维护变量的时候 为每一个使用该变量的线程提供一个独立的变量副本，即每个线程内部都会有一个该变量，这样同时多个线程访问该变量并不会彼此相互影响，
        // 因此他们使用的都是自己从内存中拷贝过来的变量的副本， 这样就不存在线程安全问题，也不会影响程序的执行性能。

        // ThreadLocal是java多线程中 牺牲空间获取线程隔离的方法，避免上锁，即每个线上保持对ThreadLocal<T>对象T的副本。
        // 线程在访问变量时，操作的是该线程独有的变量副本，彻底封闭在每个访问的线程中，并发问题也完全消除了。

        // 2 withInitial
        // DateFormatter 类是非线程安全的。使用构造函数创建一个线程安全的DateFormatter对象，并输出日期，如“01-Jan-1970”。
        ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));
        System.out.println(formatter.get().getFormat());

        System.out.println(formatter.get().getFormat().format(new Date()));

        // 3 报错
//        check(x -> x > 5); the lambda expression could be inferred as IntPred or Predicate<Integer> so the overload is ambiguous.
    }

//    boolean check(Predicate<Integer> predicate){
//        return false;
//    }
//    boolean check(IntPred predicate){
//        return true;
//    }
}

interface Function<T, R> {
    R apply(T t);
}

interface IntPred {
    boolean test(Integer value);
}

