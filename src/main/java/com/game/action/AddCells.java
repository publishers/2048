package com.game.action;

public class AddCells {
    private ActionField actionField;

    public AddCells(ActionField actionField) {
        this.actionField = actionField;
    }

    public boolean execute() {
        return actionField.addValueToRandomCell();
    }

}
