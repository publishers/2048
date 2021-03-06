package com.ui;

import com.game.model.Cell;
import com.game.model.Field;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FieldDrawer {
    private static final int MAX_FIELD_SIZE = 500;
    private static final int RED = 50;
    private static final int GREEN = 250;
    private static final int BLUE = 111;
    private static final int MAX_COLOR = 255;
    private static final int OFFSET_X = 50;
    private static final int OFFSET_Y = 80;
    private static final int MAX_FONT_SIZE = 50;

    private final int numberOfCells;
    private final Field field;
    private final int radius;

    public FieldDrawer(int numberOfCells, Field field) {
        this.numberOfCells = numberOfCells;
        this.field = field;
        radius = MAX_FIELD_SIZE / numberOfCells;
    }

    public void drawField(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, MAX_FIELD_SIZE, MAX_FIELD_SIZE);
        gc.setLineWidth(1);

        for (int i = 0; i < MAX_FIELD_SIZE; i += radius) {
            gc.moveTo(i, 0);
            gc.lineTo(i, MAX_FIELD_SIZE);

            gc.moveTo(0, i);
            gc.lineTo(MAX_FIELD_SIZE, i);
        }
        gc.stroke();
        printField(field, canvas);
    }

    private void printField(Field field, Canvas canvas) {
        Cell[][] cells = field.getCells();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[i][j];
                if (cell.getCellValue() != 0) {
                    double x = convertCoordinate(j);
                    double y = convertCoordinate(i);
                    gc.setFill(Color.rgb(RED,
                            (GREEN * cell.getCellValue()) % MAX_COLOR,
                            (BLUE * cell.getCellValue()) % MAX_COLOR));
                    gc.fillRect(x, y, radius, radius);
                    int fontSizeCalculation = MAX_FONT_SIZE - (cell.getCellValue()) % 25;
                    gc.setFont(Font.font(fontSizeCalculation));
                    String cellValue = String.valueOf(cell.getCellValue());
                    int additionalOffsetX = 0;
                    if(cellValue.length() > 1) {
                        additionalOffsetX = 15 * (cellValue.length() - 1);
                    }
                    gc.strokeText(cellValue, x + OFFSET_X - additionalOffsetX, y + OFFSET_Y);
                }
            }
        }
        gc.stroke();
    }

    private double convertCoordinate(int digit) {
        return (MAX_FIELD_SIZE / numberOfCells * digit);
    }


}
