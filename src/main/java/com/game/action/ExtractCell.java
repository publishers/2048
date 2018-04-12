package com.game.action;

import com.game.model.Cell;

@FunctionalInterface
public interface ExtractCell {
    Cell extract(int i, int j);
}
