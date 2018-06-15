package com.game;

import com.game.action.ActionField;
import com.game.action.AddCells;
import com.game.action.PrintField;
import com.game.command.Command;
import com.game.command.impl.DownCommand;
import com.game.command.impl.LeftCommand;
import com.game.command.impl.RightCommand;
import com.game.command.impl.UpCommand;
import com.game.model.Cell;
import com.game.model.Field;
import com.ui.FieldDraw;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Game implements Initializable {

    @FXML
    private Canvas canvasField;

    private FieldDraw fieldDraw;

    private static final int DEFAULT_FIELD_SIZE = 3;
    private Map<String, Command> actions;
    private AddCells addCells;
    private PrintField printField;

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

    private Map<String, Command> initActions(ActionField actionField) {
        Command upCommand = new UpCommand(actionField);
        Command downCommand = new DownCommand(actionField);
        Command leftCommand = new LeftCommand(actionField);
        Command rightCommand = new RightCommand(actionField);

        Map<String, Command> actions = new HashMap<>();
        actions.put("up", upCommand);
        actions.put("down", downCommand);
        actions.put("left", leftCommand);
        actions.put("right", rightCommand);

        return actions;
    }

    private void checkFieldSize(int fieldSize) {
        if (fieldSize < 3) {
            throw new IllegalArgumentException("Field cannot be less then 3. Actual: " + fieldSize);
        }
    }

//    private void start() {
//        Scanner scanner = new Scanner(System.in);
//        addCells.execute();
//        printField.execute();
//        System.out.println("~~~~~~~~~~~~~~~~~");
//        String action;
//        Deque<Command> executedCommands = new ArrayDeque<>();
//        do {
//            action = scanner.nextLine();
//            if (action.equals("undo")) {
//                if (executedCommands.size() > 0) {
//                    executedCommands.removeLast().undo();
//                }
//            } else {
//                Command command = actions.get(action);
//                if (command != null) {
//                    command.execute();
//                    executedCommands.add(command);
//                    addCells.execute();
//                }
//            }
//            printField.execute();
//            System.out.println("~~~~~~~~~~~~~~~~~");
//        } while (!action.equals("exit"));
//        System.out.println("Game is finished");
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Field field = initField(4);
        ActionField actionField = new ActionField(field);
        fieldDraw = new FieldDraw(4, field);
        printField = new PrintField(actionField);
        addCells = new AddCells(actionField);
        actions = initActions(actionField);
        fieldDraw.drawField(canvasField);
    }

    @FXML
    public void right(ActionEvent actionEvent) {
        doAction("right");
    }

    @FXML
    public void left(ActionEvent actionEvent) {
        doAction("left");
    }

    @FXML
    public void up(ActionEvent actionEvent) {
        doAction("up");
    }

    @FXML
    public void down(ActionEvent actionEvent) {
        doAction("down");
    }

    private void doAction(String action){
        actions.get(action).execute();
        addCells.execute();
        fieldDraw.drawField(canvasField);
        printField.execute();
    }
}
