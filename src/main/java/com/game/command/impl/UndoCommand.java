package com.game.command.impl;

import com.game.action.ActionField;

public class UndoCommand extends CommonCommand {

    public UndoCommand(ActionField actionField) {
        super(actionField);
    }

    @Override
    public void execute() {
        actionField.undo();
    }
}
