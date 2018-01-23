package com.game.command.impl;

import com.game.action.ActionField;
import com.game.command.Command;

public class PrintCommand implements Command {
  private ActionField actionField;

  public PrintCommand(ActionField actionField) {
    this.actionField = actionField;
  }

  @Override
  public void execute() {
    actionField.print();
  }

  @Override
  public void undo() {
    throw new UnsupportedOperationException();
  }
}
