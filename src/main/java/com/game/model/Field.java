package com.game.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Cell[][] cells;
    private List<int[][]> fieldHistory;
    private int[][] firstState;

    public Field(Cell[][] cells) {
        this.cells = cells;
        if (cells.length != cells[0].length) {
            throw new IllegalStateException("Field must be NxN");
        }
        fieldHistory = new ArrayList<>();
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Cell[] cell : cells) {
            for (int j = 0; j < cells.length; j++) {
                builder.append(cell[j].getCellValue())
                        .append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public void saveState() {
        int[][] newFieldState = new int[cells.length][cells.length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                newFieldState[i][j] = cells[i][j].getCellValue();
            }
        }
        fieldHistory.add(newFieldState);
        if(fieldHistory.size() == 1) {
            firstState = fieldHistory.get(0);
        }
    }

    public void undo() {
        int[][] prevFieldState;
        if (fieldHistory.size() > 1) {
            prevFieldState = fieldHistory.remove(fieldHistory.size() - 2);
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells.length; j++) {
                    cells[i][j].setCellValue(prevFieldState[i][j]);
                }
            }
        }
        if(fieldHistory.size() == 1) {
            fieldHistory.clear();
            fieldHistory.add(firstState);
        }
    }
}
