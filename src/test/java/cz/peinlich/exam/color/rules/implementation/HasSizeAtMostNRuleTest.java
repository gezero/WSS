package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGrid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HasSizeAtMostNRuleTest {

    ClassPathResource singleCellGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/specification_grid.txt");
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

    @Test
    public void test_blue_red_neighbors_from_specification_grid() throws IOException {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(singleCellGridInput.getInputStream(), null);
        assertThat(grid, is(notNullValue()));

        HasSizeAtMostNRule rule = new HasSizeAtMostNRule(Color.BLUE, 3);
        Map<Cell, String> cellStringMap = rule.executeRule(grid);
        assertThat(cellStringMap.size(), is(4));


        rule = new HasSizeAtMostNRule(Color.YELLOW, 3);
        cellStringMap = rule.executeRule(grid);
        assertThat(cellStringMap.size(), is(0));


    }

}