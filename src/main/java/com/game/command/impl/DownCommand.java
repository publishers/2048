package com.game.command.impl;

import com.game.action.ActionField;
import com.game.command.Command;

public class DownCommand implements Command {
  private ActionField actionField;

  public DownCommand(ActionField actionField) {
    this.actionField = actionField;
  }

  @Override
  public void execute() {
    actionField.actionDown();
  }
}
