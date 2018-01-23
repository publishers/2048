package com.game.command.impl;

import com.game.action.ActionField;
import com.game.command.Command;

public class AddCommand implements Command {
  private ActionField actionField;

  public AddCommand(ActionField actionField) {
    this.actionField = actionField;
  }

  @Override
  public void execute() {
    actionField.addValueToRandomCell();
  }
}