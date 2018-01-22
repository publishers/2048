package com.game.action;

import com.game.model.Field;

public class FieldModifier {
  private Field field;

  public FieldModifier(Field field) {
    this.field = field;
  }

  public final void modifiedUp() {
    throw new UnsupportedOperationException();
  }

  public final void modifiedDown() {
    throw new UnsupportedOperationException();
  }

  public final void modifiedLeft() {
    throw new UnsupportedOperationException();
  }

  public final void modifiedRight() {
    throw new UnsupportedOperationException();
  }
}
