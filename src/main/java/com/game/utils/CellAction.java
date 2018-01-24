package com.game.utils;

import com.game.action.ExtractCell;
import com.game.action.UpdateCell;
import com.game.action.UpdateField;
import com.game.model.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class CellAction {

  public static void updateCells(Cell[][] cells, List<Cell> updatedCellList, UpdateCell updateCell) {
    if (updatedCellList.isEmpty()) {
      return;
    }
    int countModifiedCells = updatedCellList.size();
    for (int i = 0; i < updatedCellList.size(); i++) {
      Cell cell = updatedCellList.get(i);
      updateCell.updateCell(i, cell.getCellValue());
    }
    for (int i = countModifiedCells; i < cells.length; i++) {
      updateCell.updateCell(i, 0);
    }
  }

  public static void process(Cell[][] cells, ExtractCell extractCell, UpdateField updateField) {
    List<Cell> cellList = new ArrayList<>();
    int fieldSize = cells.length;
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        Cell cell = extractCell.extract(i, j);
        if (cell.getCellValue() != 0) {
          cellList.add(cell);
        }
      }
      List<Cell> updatedCellList = combineCells(cellList);
      if (!updatedCellList.isEmpty()) {
        updateField.updateCells(cells, updatedCellList);
      }
      cellList.clear();
    }
  }

  private static List<Cell> combineCells(List<Cell> cellList) {
    for (int i = 0; i < cellList.size() - 1; i++) {
      Cell currentCell = cellList.get(i);
      Cell nextCell = cellList.get(i + 1);
      if (currentCell.equals(nextCell)) {
        int cellSum = currentCell.getCellValue() + nextCell.getCellValue();
        currentCell.setCellValue(cellSum);
        nextCell.setCellValue(0);
      }
    }
    return cellList
            .stream()
            .filter(cell -> cell.getCellValue() != 0)
            .collect(Collectors.toList());
  }
}
