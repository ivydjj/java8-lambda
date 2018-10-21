package com.djj.languagepoints.chapter8.designmode.command;

import java.util.ArrayList;
import java.util.List;

/*
命令发起者

在构建宏时，将每一个命令实例加入 Macro 对象的列表，然后运行宏，就会按顺序执行每一条命令。
 */
// BEGIN Macro
public class Macro {

    private final List<Action> actions; // list有序列表

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }

}
// END Macro
