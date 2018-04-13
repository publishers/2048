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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Map<String, Command> actions;
    private AddCells addCells;
    private PrintField printField;

    public Game(int fieldSize) {
        Field field = initField(fieldSize);
        ActionField actionField = new ActionField(field);
        printField = new PrintField(actionField);
        addCells = new AddCells(actionField);
        actions = initActions(actionField);
    }

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

    public void start() {
        Scanner scanner = new Scanner(System.in);
        addCells.execute();
        printField.execute();
        System.out.println("~~~~~~~~~~~~~~~~~");
        String action;
        Deque<Command> executedCommands = new ArrayDeque<>();
        do {
            action = scanner.nextLine();
            if (action.equals("undo")) {
                if (executedCommands.size() > 0) {
                    executedCommands.removeLast().undo();
                }
            } else {
                Command command = actions.get(action);
                if (command != null) {
                    command.execute();
                    executedCommands.add(command);
                    addCells.execute();
                }
            }
            printField.execute();
            System.out.println("~~~~~~~~~~~~~~~~~");
        } while (!action.equals("exit"));
        System.out.println("Game is finished");
    }
}
