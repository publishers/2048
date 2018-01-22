package com.game.action;

import com.game.model.Cell;
import com.game.model.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FieldModifier {
  private Field field;

  public FieldModifier(Field field) {
    this.field = field;
  }

  public final void modifiedUp() {
    Cell[][] cells = field.getField();

    List<Cell> cellList = new ArrayList<>();
    for (int i = 0; i < cells[0].length; i++) {
      for (int j = 0; j < cells.length; j++) {
        if (cells[j][i].getCellValue() != 0) {
          cellList.add(cells[j][i]);
        }
      }
      List<Cell> updatedCellList = combineTheSameUp(cellList);
      updateCellsUp(cells, updatedCellList);
      cellList.clear();
    }
  }

  private List<Cell> combineTheSameUp(List<Cell> cellList) {
    for (int i = 0; i < cellList.size() - 1; i++) {
      Cell cell1 = cellList.get(i);
      Cell cell2 = cellList.get(i + 1);
      if (cell1.equals(cell2)) {
        int sum = cell1.getCellValue() + cell2.getCellValue();
        cell1.setCellValue(sum);
        cell2.setCellValue(0);
      }
    }
    return cellList
            .stream()
            .filter(cell -> cell.getCellValue() != 0)
            .collect(Collectors.toList());
  }

  private void updateCellsUp(Cell[][] cells, List<Cell> updatedCellList) {
    if (updatedCellList.isEmpty()) {
      return;
    }
    int lastVerticalPosition = updatedCellList.size();
    int horizontalPosition = updatedCellList.get(0).getPositionHorizontal();
    for (int i = 0; i < updatedCellList.size(); i++) {
      Cell cell = updatedCellList.get(i);
      cell.setPositionVertical(i);
      cells[i][horizontalPosition].setCellValue(cell.getCellValue());
    }
    for (int i = lastVerticalPosition; i < cells.length; i++) {
      cells[i][horizontalPosition].setCellValue(0);
    }
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
