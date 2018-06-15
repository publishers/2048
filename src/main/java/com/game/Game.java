package com.game;

import com.game.action.ActionField;
import com.game.action.AddCells;
import com.game.command.Command;
import com.game.command.impl.DownCommand;
import com.game.command.impl.LeftCommand;
import com.game.command.impl.RightCommand;
import com.game.command.impl.UpCommand;
import com.game.model.Cell;
import com.game.model.Field;
import com.ui.FieldDraw;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Game implements Initializable {
    @FXML
    private Canvas canvasField;

    private FieldDraw fieldDraw;
    private Map<KeyCode, Command> actions;
    private AddCells addCells;

    private Field initField(int fieldSize) {
        checkFieldSize(fieldSize);
        Cell[][] cells = new Cell[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        return new Field(cells);
    }

    private Map<KeyCode, Command> initActions(ActionField actionField) {
        Command upCommand = new UpCommand(actionField);
        Command downCommand = new DownCommand(actionField);
        Command leftCommand = new LeftCommand(actionField);
        Command rightCommand = new RightCommand(actionField);

        Map<KeyCode, Command> actions = new HashMap<>();
        actions.put(KeyCode.UP, upCommand);
        actions.put(KeyCode.DOWN, downCommand);
        actions.put(KeyCode.LEFT, leftCommand);
        actions.put(KeyCode.RIGHT, rightCommand);

        return actions;
    }

    private void checkFieldSize(int fieldSize) {
        if (fieldSize < 3) {
            throw new IllegalArgumentException("Field cannot be less then 3. Actual: " + fieldSize);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Field field = initField(4);
        ActionField actionField = new ActionField(field);
        fieldDraw = new FieldDraw(4, field);
        addCells = new AddCells(actionField);
        actions = initActions(actionField);
        fieldDraw.drawField(canvasField);
        canvasField.setFocusTraversable(true);
    }

    public void keyHandler(KeyEvent event) {
        Command command = actions.get(event.getCode());
        if(command != null) {
            System.out.println(event.getCode());
            command.execute();
            addCells.execute();
            fieldDraw.drawField(canvasField);
        }
    }
}
