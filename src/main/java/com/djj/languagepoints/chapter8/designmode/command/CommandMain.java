package com.djj.languagepoints.chapter8.designmode.command;

/*
命令者模式 main

使用 Lambda 表达式或是方法引用，能让代码更简洁，去除了大量样板代码，让代码意图更加明显。

在核心 Java 中，已经有一个和 Action 接口结构一致的函数接口—— Runnable 。
我们可以在实现上述宏程序中直接使用该接口，但在这个例子中，似乎 Action
是一个更符合我们待解问题的词汇，因此我们创建了自己的接口。

【命令接收者】
执行实际任务。
【命令者】
封装了所有调用【命令执行者】的信息。
【发起者】
控制一个或多个命令的顺序和执行。
【客户端】
创建具体的命令者实例。

流程：
【命令者】是接口，【具体命令者】实现了【命令者】接口
【客户端】创建【发起者】，创建【具体命令者】实例来调用【命令接收者】，【发起者】按顺序执行【具体命令者】

发起者执行时是调用命令者接口

此例中：
【客户端】CommandMain
【命令者】Action
【具体命令者】Open Save Close 这里可以通过lambda函数调用，不用具体实现类
【命令接收者】Editor
【发起者】Macro

 */
public class CommandMain{
    public static void main(String[] args) {
        Editor editor = new Editor() {
            @Override
            public void save() {
                System.out.println("save method");
            }

            @Override
            public void open() {
                System.out.println("open method");
            }

            @Override
            public void close() {
                System.out.println("close method");
            }
        };

        // 常规调用
        Macro macro = new Macro();
        macro.record(new Open(editor));
        macro.record(new Save(editor));
        macro.record(new Close(editor));
        macro.run();

        // 使用 Lambda 表达式构建宏
        // 这里不需要手动
        macro = new Macro();
        macro.record(() -> editor.open());
        macro.record(() -> editor.save());
        macro.record(() -> editor.close());
        macro.run();

        // 最好的办法：使用方法引用构建宏
        macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();
    }
}
