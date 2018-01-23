package com.game.command.impl;

import com.game.action.ActionField;
import com.game.command.Command;

public class UpCommand implements Command {
  private ActionField actionField;

  public UpCommand(ActionField actionField) {
    this.actionField = actionField;
  }

  @Override
  public void execute() {
    actionField.actionUp();
  }
}
