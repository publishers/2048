package com.game;

import com.game.action.ActionField;
import com.game.command.Command;
import com.game.command.impl.AddCommand;
import com.game.command.impl.DownCommand;
import com.game.command.impl.LeftCommand;
import com.game.command.impl.PrintCommand;
import com.game.command.impl.RightCommand;
import com.game.command.impl.UpCommand;
import com.game.model.Cell;
import com.game.model.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
  private Map<String, Command> actions;

  public Game(int fieldSize) {
    Field field = initField(fieldSize);
    ActionField actionField = new ActionField(field);
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
    Command print = new PrintCommand(actionField);
    Command addCommand = new AddCommand(actionField);

    Map<String, Command> actions = new HashMap<>();
    actions.put("up", upCommand);
    actions.put("down", downCommand);
    actions.put("left", leftCommand);
    actions.put("right", rightCommand);
    actions.put("print", print);
    actions.put("add", addCommand);

    return actions;
  }

  private void checkFieldSize(int fieldSize) {
    if (fieldSize < 4) {
      throw new IllegalArgumentException("Field cannot be less then 4. Actual: " + fieldSize);
    }
  }

  public void start() {
    Scanner scanner = new Scanner(System.in);
    Command print = actions.get("print");
    Command addValueToRandomCell = actions.get("add");
    addValueToRandomCell.execute();
    print.execute();
    System.out.println("~~~~~~~~~~~~~~~~~");
    String action;
    do {
      action = scanner.nextLine();
      Command command = actions.get(action);
      if (command != null) {
        command.execute();
        addValueToRandomCell.execute();
      }
      print.execute();
      System.out.println("~~~~~~~~~~~~~~~~~");
    } while (!action.equals("exit"));
  }
}
