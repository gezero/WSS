package cz.peinlich.exam.color.rules.implementation.predicates;

import com.google.common.base.Predicate;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 12:41
 */
public class HasSizeAtLeast implements Predicate<Integer> {
    private int size;

    public HasSizeAtLeast(int size) {
        this.size = size;
    }

    @Override
    public boolean apply(Integer integer) {
        return integer >= size;
    }
}
