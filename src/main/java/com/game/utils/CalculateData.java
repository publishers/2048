package com.game.utils;

import com.game.model.Cell;

public final class  CalculateData {
  public CalculateData() {
    throw new IllegalStateException();
  }

  public static final Cell add(Cell oneCell, Cell secondCell) {
    Cell sum = new Cell();
    int sumValues = oneCell.getCellValue() + secondCell.getCellValue();
    sum.setCellValue(sumValues);
    return sum;
  }
}
