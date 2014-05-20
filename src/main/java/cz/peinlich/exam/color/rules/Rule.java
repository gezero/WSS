package cz.peinlich.exam.color.rules;

import cz.peinlich.exam.color.grid.Grid;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:44
 */
public interface Rule {
    void checkRule(Grid grid) throws RuleException;
}
