package com.game.command.impl;

import com.game.action.ActionField;

public class DownCommand extends CommonCommand {

    public DownCommand(ActionField actionField) {
        super(actionField);
    }

    @Override
    public void execute() {
        actionField.actionDown();
    }

}
