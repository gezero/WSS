package cz.peinlich.exam.color.rules.implementation.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.structures.Structure;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * User: George
 * Date: 20.5.2014
 * Time: 12:22
 */
public abstract class RuleAboutNeighbors extends AbstractRule {

    private Predicate<Structure> sourcePredicate;
    private Predicate<Structure> neighborPredicate;
    private Predicate<Integer> sizeRequirement;

    public RuleAboutNeighbors(Predicate<Structure> sourcePredicate, Predicate<Structure> neighborPredicate, Predicate<Integer> sizeRequirement) {
        this.sourcePredicate = sourcePredicate;
        this.neighborPredicate = neighborPredicate;
        this.sizeRequirement = sizeRequirement;
    }

    /**
     * Filters structures by the source Predicate.
     * For each remaining structure
     *      get all neighbor structures and filter them by neighborPredicate.
     *      Get size of the remaining set of neighbors and check the sizeRequirement
     *      if the requirement fails, create message for all cells in the structure
     *
     * @return list of messages for each cell that was affected by requirements
     */
    @Override
    public Map<Cell, String> executeRule(Grid grid) {
        Map<Cell, String> result = new HashMap<>();
        Iterable<Structure> filter = Iterables.filter(grid.getAllStructures(), sourcePredicate);
        for (Structure satisfyingStructure : filter) {
            Iterable<Structure> rest = Iterables.filter(grid.getNeighborStructures(satisfyingStructure),
                    neighborPredicate);
            int size = 0;
            for (Structure structure : rest) {
                size++;
            }
            if (!sizeRequirement.apply(size)) {
                result.putAll(buildWrongResult(satisfyingStructure.getCells()));
            }
        }
        return result;
    }
}
