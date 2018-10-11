package com.djj.languagepoints.chapter4;

/*
【多重继承】
接口允许多重继承，因此有可能碰到两个接口包含签名相同的默认方法的情况：必须在类中重写方法

javac 并不明确应该继承哪个接口中的方法，因此编译器会报错： class Musical Carriage
inherits unrelated defaults for rock() from types Carriage and Jukebox 。

【三定律】
1. 类胜于接口。如果在继承链中有方法体或抽象的方法声明，那么就可以忽略接口中定义的方法。
2. 子类胜于父类。如果一个接口继承了另一个接口，且两个接口都定义了一个默认方法，那么子类中定义的方法胜出。
3. 没有规则三。如果上面两条规则不适用，子类要么需要实现该方法，要么将该方法声明为抽象方法。
其中第一条规则是为了让代码向后兼容。
 */
public class MultiExtends {
}

interface Jukebox {
    default String rock() {
        return "... Jukebox !";
    }
}

interface Carriage {
    default String rock() {
        return "... Carriage 。。。";
    }
}

class MusicalCarriage implements Carriage, Jukebox {
    @Override
    public String rock() {
        /*
        使用了增强的 super 语法，用来指明使用接口 Carriage 中定义的默认方法
        使用 super 关键字是指向父类，现在使用类似 InterfaceName.super 这样的语法指的是继承自父接口的方法。
         */
        return Carriage.super.rock();
    }
}