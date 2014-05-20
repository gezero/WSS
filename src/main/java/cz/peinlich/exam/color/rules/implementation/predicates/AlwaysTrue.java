package cz.peinlich.exam.color.rules.implementation.predicates;

import com.google.common.base.Predicate;
import cz.peinlich.exam.color.structures.Structure;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 13:16
 */
public class AlwaysTrue implements Predicate<Structure> {
    @Override
    public boolean apply(Structure input) {
        return true;
    }
}
