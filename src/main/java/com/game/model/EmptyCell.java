package com.game.model;

public class EmptyCell extends Cell {
  private static final int UNUSED_POSITION = -1;

  public EmptyCell() {
    super(UNUSED_POSITION, UNUSED_POSITION);
  }
}
