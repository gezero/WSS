package cz.peinlich.exam.color.rules.implementation.rules;

import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.rules.implementation.predicates.HasColor;
import cz.peinlich.exam.color.rules.implementation.predicates.HasSize;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 17:52
 */
public class DoesNotHaveAdjacentStructureOfColor extends RuleAboutNeighbors {
    private final Color neighborColor;

    public DoesNotHaveAdjacentStructureOfColor(Color sourceColor, Color neighborColor) {
        super(new HasColor(sourceColor), new HasColor(neighborColor), new HasSize(0));
        this.neighborColor = neighborColor;
    }

    @Override
    String getMessage() {
        return "Should not have neighbor structure of color" + neighborColor.toString().toLowerCase();
    }
}
