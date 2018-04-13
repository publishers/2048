package com.game.command.impl;

import com.game.action.ActionField;

public class LeftCommand extends CommonCommand {

    public LeftCommand(ActionField actionField) {
        super(actionField);
    }

    @Override
    public void execute() {
        actionField.actionLeft();
    }

}
