package cz.peinlich.exam.color.rules;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;

import java.util.Map;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:44
 */
public interface Rule {
    Map<Cell, String> executeRule(Grid grid);


}
