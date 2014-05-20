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
    final Cell[][] matrix;
    final private List<Structure> structures = new ArrayList<>();

    public ArrayListMatrixGrid(int width, int height) {
        matrix = new Cell[width][];
        for (int i = 0; i < width; i++) {
            matrix[i] = new Cell[height];
            for (int j = 0; j < height; j++) {
                matrix[i][j] = new EmptyCell(new Point(i, j));
            }
        }
    }

    @Override
    public Cell getCell(Point coordinates) {
        return matrix[coordinates.getX()][coordinates.getY()];
    }

    @Override
    public java.util.Collection<Structure> getStructures() {
        return structures;
    }

    public void setCell(Cell cell) {

    }
}
