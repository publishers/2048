package com.game;

import com.game.action.ActionField;
import com.game.action.AddCells;
import com.game.command.Command;
import com.game.command.impl.DownCommand;
import com.game.command.impl.LeftCommand;
import com.game.command.impl.RightCommand;
import com.game.command.impl.UndoCommand;
import com.game.command.impl.UpCommand;
import com.game.model.Cell;
import com.game.model.Field;
import com.ui.FieldDrawer;
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
    private static final int NUMBER_CELLS_IN_LINE = 4;

    @FXML
    private Canvas canvasField;

    private FieldDrawer fieldDrawer;
    private Map<KeyCode, Command> actions;
    private AddCells addCells;

    private Field initField(int numberCells) {
        checkFieldSize(numberCells);
        Cell[][] cells = new Cell[numberCells][numberCells];
        for (int i = 0; i < numberCells; i++) {
            for (int j = 0; j < numberCells; j++) {
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
        Command undoCommand = new UndoCommand(actionField);

        Map<KeyCode, Command> actions = new HashMap<>();
        actions.put(KeyCode.UP, upCommand);
        actions.put(KeyCode.DOWN, downCommand);
        actions.put(KeyCode.LEFT, leftCommand);
        actions.put(KeyCode.RIGHT, rightCommand);
        actions.put(KeyCode.BACK_SPACE, undoCommand);

        return actions;
    }

    private void checkFieldSize(int fieldSize) {
        if (fieldSize < 3) {
            throw new IllegalArgumentException("Field cannot be less then 3. Actual: " + fieldSize);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Field field = initField(NUMBER_CELLS_IN_LINE);
        ActionField actionField = new ActionField(field);
        fieldDrawer = new FieldDrawer(NUMBER_CELLS_IN_LINE, field);
        addCells = new AddCells(actionField);
        actions = initActions(actionField);
        fieldDrawer.drawField(canvasField);
        canvasField.setFocusTraversable(true);
    }

    public void keyHandler(KeyEvent event) {
        Command command = actions.get(event.getCode());
        if(command != null) {
            command.execute();
            if(event.getCode() != KeyCode.BACK_SPACE) {
                addCells.execute();
            }
            fieldDrawer.drawField(canvasField);
        }
    }
}
