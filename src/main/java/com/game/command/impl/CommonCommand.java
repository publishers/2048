package com.game.command.impl;

import com.game.action.ActionField;
import com.game.command.Command;

public abstract class CommonCommand implements Command {
    ActionField actionField;

    CommonCommand(ActionField actionField) {
        this.actionField = actionField;
    }

    @Override
    public void undo() {
        actionField.undo();
    }
}
