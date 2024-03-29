package cz.peinlich.exam.color.rules.implementation.predicates;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.structures.Structure;

/** Calls Integer predicate on the amount of Structures in iterable input.
 * User: George
 * Date: 20.5.2014
 * Time: 14:04
 */
public class HasAmountOfStructures implements Predicate<Iterable<Structure>> {
    private Predicate<Integer> predicate;

    public HasAmountOfStructures(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean apply(Iterable<Structure> input) {
        int size = 0;
        for (Structure structure : input) {
            ++size;
        }
        return predicate.apply(size);
    }
}
