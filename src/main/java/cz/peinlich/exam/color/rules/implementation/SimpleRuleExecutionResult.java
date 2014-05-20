package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.rules.RuleExecutionResult;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 17:22
 */
public class SimpleRuleExecutionResult implements RuleExecutionResult {

    StringBuffer buffer = new StringBuffer();
    private String name;

    public SimpleRuleExecutionResult(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void writeResult(OutputStream outputStream) {
        try {
            outputStream.write(buffer.toString().getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addResult(Cell cell, String message) {
        buffer.append(cell.getCoordinates().getX()).append(",").
                append(cell.getCoordinates().getY()).append(",").
                append(cell.getColor()).append(",").
                append(message != null ? message : "OK");
        if (cell.getComment() != null) {
            buffer.append(cell.getComment());
        }
        buffer.append("\n");
    }
}
