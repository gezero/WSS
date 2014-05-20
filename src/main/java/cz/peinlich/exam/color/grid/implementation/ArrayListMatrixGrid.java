package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.grid.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
public class ArrayListMatrixGrid implements Grid {
    private static final Logger logger = LoggerFactory.getLogger(ArrayListMatrixGrid.class);
    final List<List<Cell>> matrix;
    final private List<Structure> structures = new ArrayList<>();
    int width;
    int height;

    public ArrayListMatrixGrid(int width, int height) {
        matrix = new ArrayList<>(width);
        this.width=0;
        this.height=0;
        extendMatrix(width,height);
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
        try {
            matrix.get(cell.getCoordinates().getX()).set(cell.getCoordinates().getY(),cell);
        } catch (IndexOutOfBoundsException e) {
            extendMatrix(cell.getCoordinates().getX()+1, cell.getCoordinates().getY()+1);
            matrix.get(cell.getCoordinates().getX()).set(cell.getCoordinates().getY(),cell);
        }
    }

    private void extendMatrix(int newWidth, int newHeight) {
        logger.info("Extending matrix. Previous size {},{}",width,height);
        extendColumns(newHeight);
        addNewColumns(newWidth, newHeight);
        this.width=newWidth;
        this.height=newHeight;
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
}
