package com.game.action;

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
        for (int i = 0; i < countModifiedCells; i++) {
            Cell cell = updatedCellList.get(i);
            updateCell.updateCell(i, cell.getCellValue());
        }
        for (int i = countModifiedCells; i < cells.length; i++) {
            updateCell.updateCell(i, 0);
        }
    }

    public static void process(Cell[][] cells, CellExtractor cellExtractor, UpdateField updateField) {
        List<Cell> notEmptyCellList = new ArrayList<>();
        int fieldSize = cells.length;
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Cell cell = cellExtractor.extract(i, j);
                if (cell.getCellValue() != 0) {
                    notEmptyCellList.add(cell);
                }
            }
            List<Cell> updatedCellList = combineCells(notEmptyCellList);
            if (!updatedCellList.isEmpty()) {
                updateField.updateCells(cells, updatedCellList);
            }
            notEmptyCellList.clear();
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
