package cz.peinlich.exam.color.rules;

import java.io.FileOutputStream;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 12:18
 */
public interface RuleExecutionResult {
    String getName();

    void writeResult(FileOutputStream outputStream);
}
