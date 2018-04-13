package com.game.command.impl;

import com.game.action.ActionField;

public class RightCommand extends CommonCommand {

    public RightCommand(ActionField actionField) {
        super(actionField);
    }

    @Override
    public void execute() {
        actionField.actionRight();
    }

}
