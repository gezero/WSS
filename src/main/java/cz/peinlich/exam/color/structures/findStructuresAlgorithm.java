package cz.peinlich.exam.color.structures;

import cz.peinlich.exam.color.grid.Grid;

import java.util.Collection;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 10:38
 */
public interface FindStructuresAlgorithm {
    Collection<Structure> findStructures(Grid grid);
}
