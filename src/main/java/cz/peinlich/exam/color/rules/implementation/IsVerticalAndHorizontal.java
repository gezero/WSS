package cz.peinlich.exam.color.rules.implementation;

import com.google.common.base.Predicates;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.rules.implementation.predicates.HasColor;
import cz.peinlich.exam.color.rules.implementation.predicates.StructureIsHorizontal;
import cz.peinlich.exam.color.rules.implementation.predicates.StructureIsVertical;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:45
 */
public class IsVerticalAndHorizontal extends RuleAboutStructure {
    public IsVerticalAndHorizontal(Color sourceColor) {
        super(new HasColor(sourceColor), Predicates.or(new StructureIsHorizontal(), new StructureIsVertical()));
    }

    @Override
    String getMessage() {
        return "Related structure has to be horizontal or vertical";
    }
}
