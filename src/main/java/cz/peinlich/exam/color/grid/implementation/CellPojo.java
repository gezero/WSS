package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 9:47
 */
public class CellPojo implements Cell {
    private final Color color;
    private final Point coordinates;

    public CellPojo(Point coordinates, Color color) {
        this.coordinates = coordinates;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Point getCoordinates() {
        return coordinates;
    }
}
