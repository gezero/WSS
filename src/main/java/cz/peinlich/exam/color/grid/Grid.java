package cz.peinlich.exam.color.grid;


import java.util.Collection;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:40
 */
public interface Grid {
    Cell getCell(Point coordinates);

    Collection<Cell> getNonEmptyCells();

    int getWidth();

    int getHeight();

    Collection<Cell> getNeighbors(Point coordinates);
}
