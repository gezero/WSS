package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.grid.Structure;

import java.util.ArrayList;
import java.util.List;


/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
public class ArrayListMatrixGrid implements Grid {
    final List<List<Cell>> matrix;
    final private List<Structure> structures = new ArrayList<>();

    public ArrayListMatrixGrid(int width, int height) {
        matrix = new ArrayList<>(width);
        for (int i = 0; i < width; i++) {
            matrix.add(new ArrayList<Cell>(height));
            for (int j = 0; j < height; j++) {
                matrix.get(i).add(new EmptyCell(new Point(i, j)));
            }
        }
    }

    @Override
    public Cell getCell(Point coordinates) {
        return matrix.get(coordinates.getX()).get(coordinates.getY());
    }

    @Override
    public java.util.Collection<Structure> getStructures() {
        return structures;
    }

    public void setCell(Cell cell) {
        matrix.get(cell.getCoordinates().getX()).set(cell.getCoordinates().getY(),cell);
    }
}
