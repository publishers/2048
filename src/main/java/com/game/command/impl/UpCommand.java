package com.game.command.impl;

import com.game.action.ActionField;

public class UpCommand extends CommonCommand {

    public UpCommand(ActionField actionField) {
        super(actionField);
    }

    @Override
    public void execute() {
        actionField.actionUp();
    }
}
