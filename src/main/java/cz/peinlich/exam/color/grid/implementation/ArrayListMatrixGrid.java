package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
public class ArrayListMatrixGrid implements Grid {
    private static final Logger logger = LoggerFactory.getLogger(ArrayListMatrixGrid.class);
    final List<List<Cell>> matrix  = new ArrayList<>();
    final List<Cell> cells = new LinkedList<>();
    int width;
    int height;

    public ArrayListMatrixGrid(int width, int height) {
        this.width = 0;
        this.height = 0;
        extendMatrix(width, height);
    }

    @Override
    public Cell getCell(Point coordinates) {
        try {
            return matrix.get(coordinates.getX()).get(coordinates.getY());
        } catch (IndexOutOfBoundsException e) {
            extendMatrix(coordinates.getX() + 1, coordinates.getY() + 1);
            return matrix.get(coordinates.getX()).get(coordinates.getY());
        }
    }

    public void setCell(Cell cell) {
        checkArgument(!cell.getColor().equals(Color.EMPTY));
        checkArgument(getCell(cell.getCoordinates()).getColor().equals(Color.EMPTY));
        try {
            matrix.get(cell.getCoordinates().getX()).set(cell.getCoordinates().getY(), cell);
        } catch (IndexOutOfBoundsException e) {
            extendMatrix(cell.getCoordinates().getX() + 1, cell.getCoordinates().getY() + 1);
            matrix.get(cell.getCoordinates().getX()).set(cell.getCoordinates().getY(), cell);
        }
        cells.add(cell);
    }

    private void extendMatrix(int newWidth, int newHeight) {
        logger.info("Extending matrix. Previous size {},{}", width, height);
        extendColumns(newHeight);
        addNewColumns(newWidth, newHeight);
        this.width = newWidth;
        this.height = newHeight;
    }

    private void addNewColumns(int newWidth, int newHeight) {
        for (int i = width; i < newWidth; i++) {
            matrix.add(new ArrayList<Cell>(newHeight));
            for (int j = 0; j < newHeight; j++) {
                matrix.get(i).add(new EmptyCell(new Point(i, j)));
            }
        }
    }

    private void extendColumns(int newHeight) {
        for (int i = 0; i < width; i++) {
            List<Cell> column = matrix.get(i);
            for (int j = height; j < newHeight; j++) {
                column.add(new EmptyCell(new Point(i, j)));
            }
        }
    }

    public Collection<Cell> getNonEmptyCells() {
        return cells;
    }
}
