package com.djj.languagepoints.chapter8.solidrule.openclosedprinciple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by djj on 2018/10/17.
 *
对开闭原则的另外一种理解和传统的思维不同，那就是使用不可变对象实现开闭原则。不
 可变对象是指一经创建就不能改变的对象。
 “不可变性”一词有两种解释：观测不可变性和实现不可变性。观测不可变性是指在其他
 对象看来，该类是不可变的；实现不可变性是指对象本身不可变。实现不可变性意味着观
 测不可变性，反之则不一定成立。
 java.lang.String 宣称是不可变的，但事实上只是观测不可变，因为它在第一次调用
 hashCode 方法时缓存了生成的散列值。在其他类看来，这是完全安全的，它们看不出散列
 值是每次在构造函数中计算出来的，还是从缓存中返回的。
 之所以在这样一本讲解 Lambda 表达式的书中谈及不可变对象，是因为它们都是函数式编
 程中耳熟能详的概念，这里也是 Lambda 表达式的发源地。它们生来就符合我在本书中讲
 述的编程风格。
 我们说不可变对象实现了开闭原则，是因为它们的内部状态无法改变，可以安全地为其增
 加新的方法。新增加的方法无法改变对象的内部状态，因此对修改是闭合的；但它们又增
 加了新的行为，因此对扩展是开放的。当然，你还需留意不要改变程序其他部分的状态。
 因其天生线程安全的特性，不可变对象引起了人们的格外注意。它们没有内部状态可变，
 因此可以安全地在不同线程之间共享。
 如果我们回顾这几种方式，会发现已经偏离了传统的开闭原则。事实上，在 Bertrand
 Meyer 第一次引入这个原则时，原意是一旦实现后，类就不允许改动了。在现代敏捷开发
 环境中，完成一个类的说法很明显已经过时了。业务需求和使用方法的变化可能会让一个
 类的功能和当初设计的不同。当然这不成为忽视这一原则的理由，只是说明了所谓的原则
 只应作为指导，而不应教条地全盘接受，走向极端。
 我认为还有一点值得思考，在 Java 8 中，使用抽象插入多个类，或者使用高阶函数来实现
 开闭原则其实是一样的。因为抽象需要使用一个接口或抽象类来定义方法，这其实就是一
 种多态的使用方式。
 在 Java 8 中，任何传入高阶函数的 Lambda 表达式都由一个函数接口表示，高阶函数负责
 调用其唯一的方法，根据传入 Lambda 表达式的不同，行为也不同。这其实也是在用多态
 来实现开闭原则。
 *
 */
public class OpenClosed {
    public static void main(String[] args) {
        /*
        ThreadLocal 类就是一个很好的例子。 ThreadLocal 有一个特殊的变量，每个线程都有一个该变量的副本并与之交互。
        该类的静态方法 withInitial 是一个高阶函数，传入一个负责生成初始值的Lambda 表达式。
        这符合开闭原则，因为不用修改 ThreadLocal 类，就能得到新的行为。
        给 withInitial 方法传入不同的工厂方法，就能得到拥有不同行为的 ThreadLocal 实例。
         */

        // 实现:ThreadLocal 日期格式化器
        ThreadLocal<DateFormat> localFormatter
                = ThreadLocal.withInitial(SimpleDateFormat::new);

        DateFormat formatter = localFormatter.get();

        //  ThreadLocal 标识符
        AtomicInteger threadId = new AtomicInteger();
        ThreadLocal<Integer> localId
                = ThreadLocal.withInitial(() -> threadId.getAndIncrement());

        int idForThisThread = localId.get();
    }

}


// 衡量系统性能，并且把得到的结果绘制成图形
class MetricDataGraph {
//    // 每次想往散点图中添加新的时间点，都要修改 MetricDataGraph 类
//    public void updateUserTime(int value);
//    public void updateSystemTime(int value);
//    public void updateIoTime(int value);

    /*
    每项具体指标现在可以实现 TimeSeries 接口，在需要时能直接插入。
    比如，我们可能会有如下类： UserTimeSeries 、 SystemTimeSeries 和 IoTimeSeries 。
    如果要添加新的，比如由于虚拟化所浪费的 CPU 时间，则可增加一个新的实现了 TimeSeries 接口的类：StealTimeSeries 。
    这样，就扩展了 MetricDataGraph 类，但并没有修改它。
     */
    public void addTimeSeries(TimeSeries values){
        System.out.println(values);
    }
}

interface TimeSeries {
    void update(int value);
}


