package cz.peinlich.exam.color.grid;


/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:40
 */
public interface Grid {
    Cell getCell(Point coordinates);
    java.util.Collection<Structure> getStructures();
}
