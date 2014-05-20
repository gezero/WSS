package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.rules.implementation.predicates.CellSizePredicate;
import cz.peinlich.exam.color.rules.implementation.predicates.HasColor;
import cz.peinlich.exam.color.rules.implementation.predicates.HasSizeAtMost;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:30
 */
public class HasSizeAtMostNRule extends RuleAboutStructure {
    private final int maxSize;

    public HasSizeAtMostNRule(Color color, int maxSize) {
        super(new HasColor(color), new CellSizePredicate(new HasSizeAtMost(maxSize)));
        this.maxSize = maxSize;
    }


    @Override
    String getMessage() {
        return "Is bigger than " + maxSize;
    }
}
