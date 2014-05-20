package cz.peinlich.exam.color.grid;


/**
 * Represents One cell in the grid
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 8:40
 */
public interface Cell {
    Color getColor();

    Point getCoordinates();

    String getComment();
}
