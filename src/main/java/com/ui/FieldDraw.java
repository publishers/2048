package com.ui;

import com.game.model.Cell;
import com.game.model.Field;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Serhii Malykhin
 */
public class FieldDraw {
    private static final int MAX_FIELD_SIZE = 500;
    private static final int RED = 50;
    private static final int GREEN = 250;
    private static final int BLUE = 111;
    private static final int MAX_COLOR = 255;
    private int numberCells;
    private Field field;
    private int radius;

    public FieldDraw(int numberCells, Field field) {
        this.numberCells = numberCells;
        this.field = field;
        radius = MAX_FIELD_SIZE / numberCells;
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
                    gc.setFont(Font.font(50 - (cell.getCellValue()) % 25));
                    gc.strokeText(cell.getCellValue() + "", x + 50, y + 80);
                }
            }
        }
        gc.stroke();
    }

    private double convertCoordinate(int digit) {
        return (MAX_FIELD_SIZE / numberCells * digit);
    }


}
