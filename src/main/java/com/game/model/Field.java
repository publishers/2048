package com.game.model;

public class Field {
    private Cell[][] cells;

    public Field(Cell[][] cells) {
        this.cells = cells;
        if (cells.length != cells[0].length) {
            throw new IllegalStateException("Field must be NxN");
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                builder.append(cells[i][j].getCellValue())
                        .append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
