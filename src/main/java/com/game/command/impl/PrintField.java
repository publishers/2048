package com.game.command.impl;

import com.game.action.ActionField;

public class PrintField {
  private ActionField actionField;

  public PrintField(ActionField actionField) {
    this.actionField = actionField;
  }

  public void execute() {
    actionField.print();
  }

}
