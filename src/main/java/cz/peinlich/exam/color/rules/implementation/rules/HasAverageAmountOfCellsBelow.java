package cz.peinlich.exam.color.rules.implementation.rules;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.structures.Structure;

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
        int size = 0;
        int cells = 0;
        for (Structure structure : input) {
            ++size;
            cells += structure.getCells().size();
        }
        return (double) cells / (double) size < average;
    }
}
