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
    int cellSize = cells.length;
    for (int i = 0; i < cellSize; i++) {
      for (int j = 0; j < cellSize; j++) {
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
}
