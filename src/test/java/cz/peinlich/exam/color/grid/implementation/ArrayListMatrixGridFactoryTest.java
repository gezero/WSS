package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArrayListMatrixGridFactoryTest {

    @Test
    public void testParse() throws Exception {
        String input = "7,22,G, Green structure #1";
        Cell cell = ArrayListMatrixGridFactory.parse(input);
        assertThat(cell.getCoordinates().getX(),is(7));
        assertThat(cell.getCoordinates().getY(),is(22));
        assertThat(cell.getColor(),is(Color.GREEN));
    }
}