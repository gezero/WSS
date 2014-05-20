package cz.peinlich.exam.color.rules.implementation.predicates;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.structures.Structure;

import com.google.common.base.Predicate;

import java.util.Collection;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:42
 */
public class StructureIsHorizontal implements Predicate<Structure> {
    @Override
    public boolean apply(Structure input) {
        Collection<Cell> cells = input.getCells();
        Integer position=null;
        for (Cell cell : cells) {
            if (position ==null){
                position = cell.getCoordinates().getX();
            } else {
                if (position!= cell.getCoordinates().getX()){
                    return false;
                }
            }
        }
        return true;
    }
}
