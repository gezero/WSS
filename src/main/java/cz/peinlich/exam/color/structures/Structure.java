package cz.peinlich.exam.color.structures;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;

import java.util.Collection;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:41
 */
public interface Structure {
    Collection<Cell> getCells();

    Color getColor();
}
