package cz.peinlich.exam.color.rules.implementation.predicates;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.structures.Structure;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 12:40
 */
public class HasColor implements Predicate<Structure> {

    private Color color;

    public HasColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean apply(Structure structure) {
        return structure.getColor().equals(color);
    }
}
