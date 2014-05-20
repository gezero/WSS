package cz.peinlich.exam.color.rules.implementation;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.structures.Structure;

import java.util.HashMap;
import java.util.Map;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 12:22
 */
public abstract class HasAmountOfSpecifiedNeighborStructuresRule extends AbstractRule {

    private Predicate<Structure> sourcePredicate;
    private Predicate<Structure> neighborPredicate;
    private Predicate<Integer> sizeRequirement;

    public HasAmountOfSpecifiedNeighborStructuresRule(Predicate<Structure> sourcePredicate, Predicate<Structure> neighborPredicate, Predicate<Integer> sizeRequirement) {
        this.sourcePredicate = sourcePredicate;
        this.neighborPredicate = neighborPredicate;
        this.sizeRequirement = sizeRequirement;
    }

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
