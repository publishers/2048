package com;

import com.game.Game;

public class DemoRunGame {
    public static void main(String[] args) {
        int fieldSize = 3;
        Game game = new Game(fieldSize);
        game.start();
    }
}
