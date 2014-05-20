package cz.peinlich.exam.color.grid;


import cz.peinlich.exam.color.structures.Structure;

import java.util.Collection;

/**
 * Represents the grid. Contains cells. Each cell has color
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 8:40
 */
public interface Grid {
    /** Name of the grid is later used to create output filename */
    String getName();
    int getWidth();
    int getHeight();

    Cell getCell(Point coordinates);

    /**
     * This collection contains cells in the order as they were added to the grid. The order is important to keep
     */
    Collection<Cell> getNonEmptyCells();

    Collection<Cell> getNeighbors(Point coordinates);
    Collection<Structure> getNeighborStructures(Structure structure);

    Collection<Structure> getAllStructures();

}
