package cz.peinlich.exam.color.rules.implementation.predicates;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.structures.Structure;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:33
 */
public class CellSizePredicate implements Predicate<Structure> {
    private Predicate<Integer> integerPredicate;

    public CellSizePredicate(Predicate<Integer> integerPredicate) {
        this.integerPredicate = integerPredicate;
    }

    @Override
    public boolean apply(Structure input) {
        return integerPredicate.apply(input.getCells().size());
    }
}
