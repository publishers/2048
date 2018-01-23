package com.game.utils;

import com.game.model.Cell;

import java.util.List;
import java.util.stream.Collectors;

public class CellList {
  public static final List<Cell> combineClients(List<Cell> cellList) {
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
