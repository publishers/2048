package com.game.action;

import com.game.model.Cell;
import com.game.model.Field;

import java.util.ArrayList;
import java.util.List;

import static com.game.utils.CellList.combineClients;

public class ActionField {
  private Field field;

  public ActionField(Field field) {
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
      List<Cell> updatedCellList = combineClients(cellList);
      updateCellsUp(cells, updatedCellList);
      cellList.clear();
    }
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
      for (int j = cells.length - 1; j >= 0; j--) {
        if (cells[j][i].getCellValue() != 0) {
          cellList.add(cells[j][i]);
        }
      }
      List<Cell> updatedCellList = combineClients(cellList);
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
      List<Cell> updatedCellList = combineClients(cellList);
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
      List<Cell> updatedCellList = combineClients(cellList);
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
