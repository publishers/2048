package com.game.command.impl;

import com.game.action.ActionField;
import com.game.command.Command;

public class RightCommand implements Command {
  private ActionField actionField;

  public RightCommand(ActionField actionField) {
    this.actionField = actionField;
  }

  @Override
  public void execute() {
    actionField.actionRight();
  }
}
