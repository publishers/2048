package com.game.model;

public class Field {
  private Cell[][] cells;

  public Field(Cell[][] cells) {
    this.cells = cells;
    if(cells.length != cells[0].length){
      throw new IllegalStateException("Field must be NxN");
    }
  }

  public Cell[][] getCells() {
    return cells;
  }
}
