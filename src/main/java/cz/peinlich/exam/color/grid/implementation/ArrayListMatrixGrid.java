package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.structures.FindStructuresAlgorithm;
import cz.peinlich.exam.color.structures.Structure;
import cz.peinlich.exam.color.structures.implementation.FloodFillAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
public class ArrayListMatrixGrid implements Grid {
    private static final Logger logger = LoggerFactory.getLogger(ArrayListMatrixGrid.class);
    final List<List<Cell>> matrix = new ArrayList<>();
    final List<Cell> cells = new LinkedList<>();
    Map<Cell,Structure> structureMap = new HashMap<>();
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
        return new LinkedList<>(cells);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Collection<Cell> getNeighbors(Point coordinates) {
        List<Cell> neighbors = new ArrayList<>(4);
        if (coordinates.getX() > 0) {
            neighbors.add(getCell(coordinates.move(-1, 0)));
        }
        if (coordinates.getY() > 0) {
            neighbors.add(getCell(coordinates.move(0, -1)));
        }
        neighbors.add(getCell(coordinates.move(1,0)));
        neighbors.add(getCell(coordinates.move(0,1)));
        return neighbors;
    }

    public Structure getStructure(Point coordinates) {
        return structureMap.get(getCell(coordinates));
    }

    public void calculateStructures(FindStructuresAlgorithm algorithm) {
        structureMap = algorithm.findStructures(this);
    }

    @Override
    public Collection<Structure> getNeighborStructures(Structure structure) {
        Set<Cell> cells = new HashSet<>();
        for (Cell cell : structure.getCells()) {
            cells.addAll(getNeighbors(cell));
        }
        cells.removeAll(structure.getCells());
        Set<Structure> structures = new HashSet<>();
        for (Cell cell : cells) {
            Structure structureToAdd = getStructure(cell);
            if (structureToAdd!=null) {
                structures.add(structureToAdd);
            }
        }
        return structures;
    }

    private Structure getStructure(Cell cell) {
        return getStructure(cell.getCoordinates());
    }

    private Collection<Cell> getNeighbors(Cell cell) {
        return getNeighbors(cell.getCoordinates());
    }
}
