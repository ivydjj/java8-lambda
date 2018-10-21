package com.djj.languagepoints.chapter8.designmode.observer;

/*
观察者模式

例：
NASA 和外星人都对登陆到月球上的东西感兴趣，都希望可以记录这些信息。
NASA 希望确保阿波罗号上的航天员成功登月；外星人则希望在 NASA 注意力分散之时进犯地球。

总结：
无论使用观察者模式或策略模式，实现时采用 Lambda 表达式还是传统的类，取决于策略和观察者代码的复杂度。
从某种角度来说，将大量代码塞进一个方法会让可读性变差是决定如何使用Lambda 表达式的黄金法则。

 */
public class ObserverMain {
    public static void main(String[] args) {
        classBasedExample();
        lambdaBasedExample();
    }

    // 使用类的方式构建用户代码
    private static void classBasedExample() {
        Moon moon = new Moon();
        moon.startSpying(new Nasa());
        moon.startSpying(new Aliens());

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }

    // 使用 Lambda 表达式构建用户代码
    // 不需要类 Aliens Nasa
    private static void lambdaBasedExample() {
        Moon moon = new Moon();

        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("We made it!");
        });

        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("They're distracted, lets invade earth!");
        });

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }
}
