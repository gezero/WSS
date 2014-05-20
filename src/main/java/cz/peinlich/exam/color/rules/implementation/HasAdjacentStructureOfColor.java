package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.rules.implementation.predicates.HasColor;
import cz.peinlich.exam.color.rules.implementation.predicates.HasSize;
import cz.peinlich.exam.color.rules.implementation.predicates.HasSizeAtLeast;
import cz.peinlich.exam.color.rules.implementation.predicates.HasSizeAtMost;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 12:36
 */
public class HasAdjacentStructureOfColor extends RuleAboutNeighbors {
    private final Color neighborColor;

    public HasAdjacentStructureOfColor(Color sourceColor, Color neighborColor) {
        super(new HasColor(sourceColor), new HasColor(neighborColor), new HasSizeAtLeast(1));
        this.neighborColor = neighborColor;
    }

    @Override
    String getMessage() {
        return "Does not have neighbor of color " + neighborColor.toString().toLowerCase();
    }
}
