package cz.peinlich.exam.color.rules;

import cz.peinlich.exam.color.grid.Grid;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 12:11
 */
public interface RuleEngine {
    void registerRule(Rule rule);

    RuleExecutionResult executeRules(Grid grid);
}
