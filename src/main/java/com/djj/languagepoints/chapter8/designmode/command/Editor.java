package com.djj.languagepoints.chapter8.designmode.command;

/*
文本编辑器

像 open 、 save 这样的操作称为命令
用一个统一的接口来概括这些不同的操作，这里将这个接口叫作 Action ，它代表了一个操作，所有的命令都要实现该接口
 */
// BEGIN Editor
public interface Editor {

    void save();

    void open();

    void close();

}
// END Editor


