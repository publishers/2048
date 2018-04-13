package com.game.action;

public class AddCells {
    private ActionField actionField;

    public AddCells(ActionField actionField) {
        this.actionField = actionField;
    }

    public void execute() {
        actionField.addValueToRandomCell();
        actionField.saveNewState();
    }

}
