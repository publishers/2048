package com.game.action;

import com.game.model.Cell;

import java.util.List;

public interface UpdateField {
  void updateCells(Cell[][] cells, List<Cell> updatedCellList);
}
