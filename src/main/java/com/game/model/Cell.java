package com.game.model;

public class Cell {
  private int positionVertical;
  private int positionHorizontal;
  private int cellValue;

  public Cell(int positionVertical, int positionHorizontal) {
    this.positionHorizontal = positionHorizontal;
    this.positionVertical = positionVertical;
  }

  public int getPositionVertical() {
    return positionVertical;
  }

  public void setPositionVertical(int positionVertical) {
    this.positionVertical = positionVertical;
  }

  public int getPositionHorizontal() {
    return positionHorizontal;
  }

  public void setPositionHorizontal(int positionHorizontal) {
    this.positionHorizontal = positionHorizontal;
  }

  public int getCellValue() {
    return cellValue;
  }

  public void setCellValue(int cellValue) {
    this.cellValue = cellValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Cell cell = (Cell) o;

    return cellValue == cell.cellValue;
  }

  @Override
  public int hashCode() {
    return cellValue;
  }

  @Override
  public String toString() {
    return "Cell{" +
            "positionVertical=" + positionVertical +
            ", positionHorizontal=" + positionHorizontal +
            ", cellValue=" + cellValue +
            '}';
  }
}
