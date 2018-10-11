package com.djj.languagepoints.chapter4;

/*
接口 默认方法 default

增加默认方法主要是为了在接口上向后兼容。让类中重写方法的优先级高于默认方法能简化很多继承问题。
 */
public class DefaultMethod {
    /*
        二进制接口的兼容性

        Java 8 中为 Collection 接口增加了 stream 方法，打破了二进制兼容性，
        在 JDK 之外实现 Collection 接口的类，在 Java 8 中无法通过编译，
        即使已有一个编译好的版本，在 JVM 加载该类时，类加载器仍然会引发异常。

         Collection 接口告诉它所有的子类：“如果你没有实现 stream 方法，就使用我的吧。”
         接口中这样的方法叫作默认方法，在任何接口中，无论函数接口还是非函数接口，都可以使用该方法。

         Iterable 接口中也新增了一个默认方法： forEach ，该方法功能和 for 循环类似，但是允许用户使用一个 Lambda 表达式作为循环体。

         【default】
         这个关键字告诉 javac 用户真正需要的是为接口添加一个新方法。
        接口没有成员变量，因此默认方法只能通过调用子类的方法来修改子类本身，避免了对子类的实现做出各种假设。

        【重写】
        默认方法的重写规则也有一些微妙之处。从最简单的情况开始来看：没有重写。
        任何时候，一旦与类中定义的方法产生冲突，都要优先选择类中定义的方法。
     */

    public static void main(String[] args) {
        Parent parent = new ParentImpl();
        parent.welcome(); // Parent: Hi!

        Child child = new ChildImpl();
        child.welcome(); // Child: Hi!

        // 原因在于，与接口中定义的默认方法相比，类中重写的方法更具体
        parent = new OverridingParent();
        parent.welcome(); // Class Parent: Hi!

        // 类中重写的方法优先级高于接口中定义的默认方法
        child = new OverridingChild();
        child.welcome(); // OverridingChild: Hi!

        CustomerParent customerParent = new CustomerParentImpl();
        customerParent.welcome(); // CustomerParentImpl: Hi!
    }

}

/*
假设已实现了一个定制的列表 MyCustomList ，该类中有一个 addAll 方法，如果新的 List
接口也增加了一个默认方法 addAll ，该方法将对列表的操作代理到 add 方法。如果类中重
写的方法没有默认方法的优先级高，那么就会破坏已有的实现。
 */
interface CustomerParent{
    default void welcome() {
        System.out.println("Parent: Hi!");
    }
}

class CustomerParentImpl implements CustomerParent {

    public void welcome() {
        System.out.println("CustomerParentImpl: Hi!");
    }
}

interface Parent {
    void message(String body);
    default void welcome() {
        message("Parent: Hi!");
    }
    String getLastMessage();
}

class ParentImpl implements Parent {
    private String body;

    @Override
    public void message(String body) {
        this.body = body;
        System.out.println(body);
    }

    @Override
    public String getLastMessage() {
        return body;
    }

}

class OverridingParent extends ParentImpl {
    @Override
    public void welcome() {
        message("Class Parent: Hi!");
    }
}

interface Child extends Parent {
    @Override
    public default void welcome() {
        message("Child: Hi!");
    }
}

class ChildImpl implements Child {
    private String body;

    @Override
    public void message(String body) {
        this.body = body;
        System.out.println(body);
    }

    @Override
    public String getLastMessage() {
        return body;
    }

}

class OverridingChild extends OverridingParent implements Child {
    @Override
    public void welcome() {
        message("OverridingChild: Hi!");
    }
}