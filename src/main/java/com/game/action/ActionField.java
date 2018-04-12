package com.game.action;

import com.game.model.Cell;
import com.game.model.EmptyCell;
import com.game.model.Field;

import static com.game.action.CellAction.process;
import static com.game.action.CellAction.updateCells;

public class ActionField {
    private Field field;

    public ActionField(Field field) {
        this.field = field;
    }

    public final void actionUp() {
        Cell[][] cells = field.getCells();
        process(cells,
                (i, j) -> cells[j][i],
                (updatedCells, updatedCellList) -> {
                    int horizontalPosition = updatedCellList.get(0).getPositionHorizontal();
                    updateCells(cells, updatedCellList,
                            (cellPosition, cellValue) ->
                                    cells[cellPosition][horizontalPosition].setCellValue(cellValue));
                });
    }

    public final void actionDown() {
        Cell[][] cells = field.getCells();
        process(cells,
                (i, j) -> cells[cells.length - j - 1][i],
                (updatedCells, updatedCellList) -> {
                    int horizontalPosition = updatedCellList.get(0).getPositionHorizontal();
                    updateCells(cells, updatedCellList,
                            (cellPosition, cellValue) ->
                                    cells[cells.length - 1 - cellPosition][horizontalPosition].setCellValue(cellValue));
                });
    }

    public final void actionLeft() {
        Cell[][] cells = field.getCells();
        process(cells,
                (i, j) -> cells[i][j],
                (updatedCells, updatedCellList) -> {
                    int verticalPosition = updatedCellList.get(0).getPositionVertical();
                    updateCells(updatedCells, updatedCellList,
                            (cellPosition, cellValue) ->
                                    updatedCells[verticalPosition][cellPosition].setCellValue(cellValue));
                });
    }

    public final void actionRight() {
        Cell[][] cells = field.getCells();
        process(cells,
                (i, j) -> cells[i][cells.length - j - 1],
                (updatedCells, updatedCellList) -> {
                    int verticalPosition = updatedCellList.get(0).getPositionVertical();
                    updateCells(cells, updatedCellList,
                            (cellPosition, cellValue) ->
                                    cells[verticalPosition][cells[0].length - cellPosition - 1].setCellValue(cellValue));
                });
    }

    public final boolean addValueToRandomCell() {
        Cell[][] cells = field.getCells();
        Cell cell = findEmptyCell(cells);
        boolean result = true;
        if (cell.getPositionHorizontal() == -1) {
            result = false;
        } else {
            cell.setCellValue(2);
        }
        return result;
    }

    private static Cell findEmptyCell(Cell[][] cells) {
        int fieldSize = cells.length;
        Cell cell;
        int numberLoopAction = 0;
        do {
            int vertical = (int) (Math.random() * fieldSize);
            int horizontal = (int) (Math.random() * fieldSize);
            cell = cells[vertical][horizontal];
            numberLoopAction++;
        } while (cell.getCellValue() != 0 && numberLoopAction < 10 * fieldSize);

        boolean isFindEmptyCell = true;

        if (numberLoopAction == 10 * fieldSize) {
            isFindEmptyCell = false;
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (cells[i][j].getCellValue() == 0) {
                        cell = cells[i][j];
                        isFindEmptyCell = true;
                        break;
                    }
                }
                if (isFindEmptyCell) break;
            }
        }
        if (!isFindEmptyCell) {
            return new EmptyCell();
        }
        return cell;
    }

    public final void print() {
        System.out.println(field.toString());
    }
}
