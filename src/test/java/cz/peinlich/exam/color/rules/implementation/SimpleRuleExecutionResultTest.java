package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.grid.implementation.EmptyCell;
import org.junit.Test;

import java.io.OutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SimpleRuleExecutionResultTest {

    @Test
    public void testGetName() throws Exception {
        OutputStream mockStream=mock(OutputStream.class);
        SimpleRuleExecutionResult result = new SimpleRuleExecutionResult("name");
        assertThat(result.getName(),is("name"));
        result.addResult(new EmptyCell(new Point(1,1)),"message");
        result.writeResult(mockStream);
        //TODO: add check of what is written into mockStream and some r
    }
}