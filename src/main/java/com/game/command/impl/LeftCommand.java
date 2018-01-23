package com.game.command.impl;

import com.game.action.ActionField;
import com.game.command.Command;

public class LeftCommand implements Command {
  private ActionField actionField;

  public LeftCommand(ActionField actionField) {
    this.actionField = actionField;
  }

  @Override
  public void execute() {
    actionField.actionLeft();
  }
}
