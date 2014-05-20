package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Point;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class EmptyCellTest {

    @Test
    public void testGetComment() throws Exception {
        EmptyCell emptyCell = new EmptyCell(new Point(1, 1));
        assertThat(emptyCell.getComment(),is(notNullValue()));
    }
}