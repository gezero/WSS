package cz.peinlich.exam.color.rules.implementation.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.structures.Structure;

import java.util.HashMap;
import java.util.Map;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:28
 */
public abstract class RuleAboutStructure extends AbstractRule {
    private Predicate<Structure> filterPredicate;
    private Predicate<Structure> structurePredicate;

    public RuleAboutStructure(Predicate<Structure> filterPredicate, Predicate<Structure> structurePredicate) {
        this.filterPredicate = filterPredicate;
        this.structurePredicate = structurePredicate;
    }
    /**
     * Filters structures by the source Predicate.
     * For each remaining structure
     *      Check the structurePredicate.
     *      If the predicate fails, create message for all cells in the structure
     *
     * @return list of messages for each cell that was affected by requirements
     */
    @Override
    public Map<Cell, String> executeRule(Grid grid) {
        Map<Cell, String> result = new HashMap<>();
        Iterable<Structure> filter = Iterables.filter(grid.getAllStructures(), filterPredicate);
        for (Structure satisfyingStructure : filter) {
            if (!structurePredicate.apply(satisfyingStructure)) {
                result.putAll(buildWrongResult(satisfyingStructure.getCells()));
            }
        }
        return result;
    }
}
