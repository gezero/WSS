package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;


/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:56
 */
public class EmptyCell implements Cell {
    final Point point;

    public EmptyCell(Point point) {
        this.point = point;
    }

    @Override
    public Color getColor() {
        return Color.EMPTY;
    }

    @Override
    public Point getCoordinates() {
        return point;
    }

    @Override
    public String getComment() {
        return null;
    }
}
