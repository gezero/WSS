package cz.peinlich.exam.color.rules.implementation.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.structures.Structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:54
 */
public abstract class RuleAboutSetOfStructures extends AbstractRule {
    private Predicate<Structure> filterPredicate;
    private Predicate<Iterable<Structure>> conditionPredicate;

    public RuleAboutSetOfStructures(Predicate<Structure> filterPredicate, Predicate<Iterable<Structure>> conditionPredicate) {
        this.filterPredicate = filterPredicate;
        this.conditionPredicate = conditionPredicate;
    }

    @Override
    public Map<Cell, String> executeRule(Grid grid) {
        Map<Cell, String> result = new HashMap<>();
        Iterable<Structure> filter = Iterables.filter(grid.getAllStructures(), filterPredicate);
        Set<Structure> structureSet = new HashSet<>();
        for (Structure structure : filter) {
            structureSet.add(structure);
        }
        if (!conditionPredicate.apply(structureSet)) {
            for (Structure structure : structureSet) {
                result.putAll(buildWrongResult(structure.getCells()));
            }
        }
        return result;
    }
}
