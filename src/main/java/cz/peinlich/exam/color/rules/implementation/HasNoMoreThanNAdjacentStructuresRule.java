package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.rules.implementation.predicates.AlwaysTrue;
import cz.peinlich.exam.color.rules.implementation.predicates.HasColor;
import cz.peinlich.exam.color.rules.implementation.predicates.HasSizeAtMost;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:08
 */
public class HasNoMoreThanNAdjacentStructuresRule extends RuleAboutNeighbors {
    private int n;

    public HasNoMoreThanNAdjacentStructuresRule(Color sourceColor, int allowedAdjacentStructures) {
        super(new HasColor(sourceColor), new AlwaysTrue(), new HasSizeAtMost(allowedAdjacentStructures));
    }

    @Override
    String getMessage() {
        return "Does have more than " + n + " adjacent structures";
    }
}
