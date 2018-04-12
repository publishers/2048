package com.game.action;

public class PrintField {
    private ActionField actionField;

    public PrintField(ActionField actionField) {
        this.actionField = actionField;
    }

    public void execute() {
        actionField.print();
    }

}
