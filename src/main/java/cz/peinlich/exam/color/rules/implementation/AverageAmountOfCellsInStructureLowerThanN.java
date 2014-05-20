package cz.peinlich.exam.color.rules.implementation;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.rules.implementation.predicates.HasColor;
import cz.peinlich.exam.color.structures.Structure;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 14:13
 */
public class AverageAmountOfCellsInStructureLowerThanN extends RuleAboutSetOfStructures {
    Color color;

    public AverageAmountOfCellsInStructureLowerThanN(Color color, double average) {
        super(new HasColor(color), new HasAverageAmountOfCellsBelow(average));
        this.color = color;
    }

    @Override
    String getMessage() {
        return "The average amount of cells of color " + color.toString().toLowerCase() + " is to high.";
    }
}
