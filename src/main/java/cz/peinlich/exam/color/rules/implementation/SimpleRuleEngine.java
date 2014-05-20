package cz.peinlich.exam.color.rules.implementation;

import ch.qos.logback.core.joran.spi.RuleStore;
import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.rules.Rule;
import cz.peinlich.exam.color.rules.RuleEngine;
import cz.peinlich.exam.color.rules.RuleExecutionResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 17:20
 */
public class SimpleRuleEngine implements RuleEngine {
    private List<Rule> rules= new ArrayList<>(10);

    @Override
    public void registerRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public RuleExecutionResult executeRules(Grid grid) {
        Map<Cell, String> cellStringMap= new HashMap<>();
        for (Rule rule : rules) {
             cellStringMap.putAll(rule.executeRule(grid));
        }

        SimpleRuleExecutionResult result = new SimpleRuleExecutionResult(grid.getName());
        for (Cell cell : grid.getNonEmptyCells()) {
            result.addResult(cell,cellStringMap.get(cell));
        }
        return result;
    }
}
