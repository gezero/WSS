package cz.peinlich.exam.color.structures.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGrid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.grid.implementation.CellPojo;
import cz.peinlich.exam.color.structures.FindStructuresAlgorithm;
import cz.peinlich.exam.color.structures.Structure;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 10:39
 */
public class FloodFillAlgorithm implements FindStructuresAlgorithm {
    @Override
    public Collection<Structure> findStructures(Grid grid) {
        List<Structure> list = new LinkedList<>();
        List<Cell> nonEmptyCells = new LinkedList<>(grid.getNonEmptyCells());

        while (nonEmptyCells.size()>0){
            Cell start = nonEmptyCells.get(0);
            nonEmptyCells.remove(start);
            ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();
            ArrayListMatrixGrid fillingGrid = factory.buildEmptyMatrixGrid(grid.getWidth(), grid.getHeight());
            Structure structure = findWholeStructure(start,fillingGrid,grid);
            nonEmptyCells.removeAll(structure.getCells());
            list.add(structure);
        }

        return list;
    }

    private Structure findWholeStructure(Cell start,ArrayListMatrixGrid fillingGrid, Grid grid) {
        final List<Cell> structureCells = new LinkedList<>();

        flood(start.getCoordinates(),start.getColor(),structureCells,fillingGrid,grid);
        return new StructurePojo(structureCells);
    }

    private void flood(Point point, Color color,List<Cell> structureCells, ArrayListMatrixGrid fillingGrid, Grid grid) {
        Cell cell = grid.getCell(point);
        if (cell.getColor().equals(color) && fillingGrid.getCell(point).getColor().equals(Color.EMPTY)){
            Collection<Cell> neighbors = grid.getNeighbors(point);
            fillingGrid.setCell(cell);
            structureCells.add(cell);
            for (Cell neighbor : neighbors) {
                flood(neighbor.getCoordinates(),color,structureCells,fillingGrid,grid);
            }
        }
    }


}
