package com.game.model;

public class Cell {
  private State state;
  private int cellValue;

  public Cell() {
    this.state = State.EMPTY;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public int getCellValue() {
    return cellValue;
  }

  public void setCellValue(int cellValue) {
    this.cellValue = cellValue;
  }
}
