package com.game.action;

import com.game.model.Cell;

@FunctionalInterface
public interface CellExtractor {
    Cell extract(int i, int j);
}
