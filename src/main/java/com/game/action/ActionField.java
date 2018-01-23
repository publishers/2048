package com.game.action;

import com.game.model.Cell;
import com.game.model.Field;

import static com.game.utils.CellAction.process;
import static com.game.utils.CellAction.updateCells;

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
}
