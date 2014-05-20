package cz.peinlich.exam.color.structures.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGrid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.structures.FindStructuresAlgorithm;
import cz.peinlich.exam.color.structures.Structure;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 10:39
 */
public class FloodFillAlgorithm implements FindStructuresAlgorithm {



    @Override
    public Map<Cell, Structure> findStructures(Grid grid) {
        Map<Cell, Structure> map = new HashMap<>();
        List<Cell> nonEmptyCells = new LinkedList<>(grid.getNonEmptyCells());

        while (nonEmptyCells.size() > 0) {
            Cell start = nonEmptyCells.get(0);
            nonEmptyCells.remove(start);
            ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();
            ArrayListMatrixGrid fillingGrid = factory.buildEmptyMatrixGrid(grid.getWidth(), grid.getHeight());
            Structure structure = findWholeStructure(start, fillingGrid, grid);
            nonEmptyCells.removeAll(structure.getCells());
            for (Cell cell : structure.getCells()) {
                map.put(cell, structure);
            }
        }
        return map;
    }

    private Structure findWholeStructure(Cell start, ArrayListMatrixGrid fillingGrid, Grid grid) {
        checkNotNull(start);
        final List<Cell> structureCells = new LinkedList<>();
        flood(start.getCoordinates(), start.getColor(), structureCells, fillingGrid, grid);
        return new StructurePojo(structureCells.iterator().next().getColor(), structureCells);
    }

    /**
     * From Wikipedia (http://en.wikipedia.org/wiki/Flood_fill):
     *  1. If target-color is equal to replacement-color, return.           (Here represented with combination of new and old grid)
     *  2. If the color of node is not equal to target-color, return.       (Both grids need to be checked here)
     *  3. Set the color of node to replacement-color.                      (Into the additional matrix)
     *  4. Perform Flood-fill (one step to the west of node, target-color, replacement-color).
     *     Perform Flood-fill (one step to the east of node, target-color, replacement-color).
     *     Perform Flood-fill (one step to the north of node, target-color, replacement-color).
     *     Perform Flood-fill (one step to the south of node, target-color, replacement-color).
     5. Return.
     */
    private void flood(Point point, Color color, List<Cell> structureCells, ArrayListMatrixGrid fillingGrid, Grid grid) {
        Cell cell = grid.getCell(point);
        if (cell.getColor().equals(color) && fillingGrid.getCell(point).getColor().equals(Color.EMPTY)) {
            Collection<Cell> neighbors = grid.getNeighbors(point);
            fillingGrid.setCell(cell);
            structureCells.add(cell);
            for (Cell neighbor : neighbors) {
                flood(neighbor.getCoordinates(), color, structureCells, fillingGrid, grid);
            }
        }
    }


}
