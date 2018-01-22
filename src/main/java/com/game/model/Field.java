package com.game.model;

public class Field {
  private Cell[][] field;

  public Field(Cell[][] field) {
    this.field = field;
  }

  public Cell[][] getField() {
    return field;
  }
}
