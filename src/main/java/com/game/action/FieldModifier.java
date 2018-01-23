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

  public final void actionUp() {
    Cell[][] cells = field.getField();

    List<Cell> cellList = new ArrayList<>();
    for (int i = 0; i < cells[0].length; i++) {
      for (Cell[] cell : cells) {
        if (cell[i].getCellValue() != 0) {
          cellList.add(cell[i]);
        }
      }
      List<Cell> updatedCellList = combineTheSame(cellList);
      updateCellsUp(cells, updatedCellList);
      cellList.clear();
    }
  }

  private List<Cell> combineTheSame(List<Cell> cellList) {
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
      cells[i][horizontalPosition].setCellValue(cell.getCellValue());
    }
    for (int i = lastVerticalPosition; i < cells.length; i++) {
      cells[i][horizontalPosition].setCellValue(0);
    }
  }

  public final void actionDown() {
    Cell[][] cells = field.getField();

    List<Cell> cellList = new ArrayList<>();
    for (int i = 0; i < cells[0].length; i++) {
      for (Cell[] cell : cells) {
        if (cell[i].getCellValue() != 0) {
          cellList.add(cell[i]);
        }
      }
      List<Cell> updatedCellList = combineTheSame(cellList);
      updateCellsDown(cells, updatedCellList);
      cellList.clear();
    }
  }

  private void updateCellsDown(Cell[][] cells, List<Cell> updatedCellList) {
    if (updatedCellList.isEmpty()) {
      return;
    }
    int lastVerticalPosition = updatedCellList.size();
    int horizontalPosition = updatedCellList.get(0).getPositionHorizontal();
    for (int i = updatedCellList.size() - 1; i >= 0 ; i--) {
      Cell cell = updatedCellList.get(i);
      cells[cells.length - i - 1][horizontalPosition].setCellValue(cell.getCellValue());
    }
    for (int i = cells.length - lastVerticalPosition - 1; i >= 0 ; i--) {
      cells[i][horizontalPosition].setCellValue(0);
    }
  }

  public final void actionLeft() {
    Cell[][] cells = field.getField();

    List<Cell> cellList = new ArrayList<>();
    for (Cell[] cell : cells) {
      for (int j = 0; j < cells[0].length; j++) {
        if (cell[j].getCellValue() != 0) {
          cellList.add(cell[j]);
        }
      }
      List<Cell> updatedCellList = combineTheSame(cellList);
      updateCellsLeft(cells, updatedCellList);
      cellList.clear();
    }
  }

  private void updateCellsLeft(Cell[][] cells, List<Cell> updatedCellList) {
    if (updatedCellList.isEmpty()) {
      return;
    }
    int lastHorizontalPosition = updatedCellList.size();
    int verticalPosition = updatedCellList.get(0).getPositionVertical();
    for (int i = 0; i < updatedCellList.size(); i++) {
      Cell cell = updatedCellList.get(i);
      cells[verticalPosition][i].setCellValue(cell.getCellValue());
    }
    for (int i = lastHorizontalPosition; i < cells.length; i++) {
      cells[verticalPosition][i].setCellValue(0);
    }
  }

  public final void actionRight() {
    Cell[][] cells = field.getField();

    List<Cell> cellList = new ArrayList<>();
    for (Cell[] cell : cells) {
      for (int j = cells[0].length - 1; j >= 0; j--) {
        if (cell[j].getCellValue() != 0) {
          cellList.add(cell[j]);
        }
      }
      List<Cell> updatedCellList = combineTheSame(cellList);
      updateCellsRight(cells, updatedCellList);
      cellList.clear();
    }
  }

  private void updateCellsRight(Cell[][] cells, List<Cell> updatedCellList) {
    if (updatedCellList.isEmpty()) {
      return;
    }
    int lastHorizontalPosition = updatedCellList.size();
    int verticalPosition = updatedCellList.get(0).getPositionVertical();
    for (int i = 0; i < updatedCellList.size() ; i++) {
      Cell cell = updatedCellList.get(i);
      cells[verticalPosition][cells[0].length - i - 1].setCellValue(cell.getCellValue());
    }
    for (int i = cells.length - lastHorizontalPosition - 1; i >= 0 ; i--) {
      cells[verticalPosition][i].setCellValue(0);
    }
  }
}
