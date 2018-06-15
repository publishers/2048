package com.ui;

import com.game.model.Cell;
import com.game.model.Field;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

/**
 * @author Serhii Malykhin
 */
public class FieldDraw {
    private final int MAX_FIELD_SIZE = 500;
    private int numberCells;
    private Field field;

    public FieldDraw(int numberCells, Field field) {
        this.numberCells = numberCells;
        this.field = field;
    }

    public void drawField(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, MAX_FIELD_SIZE, MAX_FIELD_SIZE);
        gc.setLineWidth(1);
        int radius = MAX_FIELD_SIZE / numberCells;
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
        gc.setFont(Font.font(20));
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[i][j];
                if(cell.getCellValue() != 0) {
                    gc.strokeText(cell.getCellValue() + "", convertCoordinate(j), convertCoordinate(i) );
                }
            }
        }
        gc.stroke();
    }

    private int convertCoordinate(int digit) {
        return (MAX_FIELD_SIZE / numberCells * digit) + 50;
    }


}
