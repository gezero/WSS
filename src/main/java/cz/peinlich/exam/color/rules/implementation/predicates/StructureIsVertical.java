package cz.peinlich.exam.color.rules.implementation.predicates;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.structures.Structure;

import java.util.Collection;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:42
 */
public class StructureIsVertical implements Predicate<Structure> {
    @Override
    public boolean apply(Structure input) {
        Collection<Cell> cells = input.getCells();
        Integer position = null;
        for (Cell cell : cells) {
            if (position == null) {
                position = cell.getCoordinates().getY();
            } else {
                if (position != cell.getCoordinates().getY()) {
                    return false;
                }
            }
        }
        return true;
    }
}
