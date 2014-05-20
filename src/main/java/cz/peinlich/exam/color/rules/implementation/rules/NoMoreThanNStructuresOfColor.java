package cz.peinlich.exam.color.rules.implementation.rules;

import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.rules.implementation.predicates.HasColor;
import cz.peinlich.exam.color.rules.implementation.predicates.HasSizeAtMost;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 14:01
 */
public class NoMoreThanNStructuresOfColor extends RuleAboutSetOfStructures {
    private Color color;

    public NoMoreThanNStructuresOfColor(Color color,int amount) {
        super(new HasColor(color), new HasAmountOfStructures(new HasSizeAtMost(amount)));
        this.color=color;
    }

    @Override
    String getMessage() {
        return "There are to many "+ color.toString().toLowerCase()+" structures";
    }
}
