package com.djj.languagepoints.chapter8.designmode.command;

// BEGIN Save
public class Save implements Action {

    private final Editor editor;

    public Save(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.save();
    }
}
// END Save
