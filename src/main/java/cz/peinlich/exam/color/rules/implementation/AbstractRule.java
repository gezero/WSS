package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.rules.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 12:42
 */
public abstract class AbstractRule implements Rule {
    Map<Cell, String> buildWrongResult(Collection<Cell> cells) {
        Map<Cell, String> map = new HashMap<>();
        String message = getMessage();
        for (Cell cell : cells) {
            map.put(cell, message);
        }
        return map;
    }

    abstract String getMessage();
}
