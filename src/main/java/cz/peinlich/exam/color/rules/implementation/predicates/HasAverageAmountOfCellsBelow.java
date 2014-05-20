package cz.peinlich.exam.color.rules.implementation.predicates;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.structures.Structure;

import java.util.HashSet;
import java.util.Set;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 14:17
 */
public class HasAverageAmountOfCellsBelow implements Predicate<Iterable<Structure>> {
    private double average;

    public HasAverageAmountOfCellsBelow(double average) {
        this.average = average;
    }

    @Override
    public boolean apply(Iterable<Structure> input) {
        int cells = 0;
        Set<Structure> set = new HashSet<>();
        for (Structure structure : input) {
            set.add(structure);
            cells += structure.getCells().size();
        }
        if (set.size() ==0){
            return true;
        }
        return (double) cells / (double) set.size() < average;
    }
}
