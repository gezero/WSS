package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGrid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.rules.implementation.rules.AverageAmountOfCellsInStructureLowerThanN;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AverageAmountOfCellsInStructureLowerThanNTest {

    ClassPathResource singleCellGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/specification_grid.txt");
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

    @Test
    public void test_blue_red_neighbors_from_specification_grid() throws IOException {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(singleCellGridInput.getInputStream(), null);
        assertThat(grid, is(notNullValue()));

        AverageAmountOfCellsInStructureLowerThanN rule = new AverageAmountOfCellsInStructureLowerThanN(Color.BLUE, 3);
        Map<Cell, String> cellStringMap = rule.executeRule(grid);
        assertThat(cellStringMap.size(), is(6));

        rule = new AverageAmountOfCellsInStructureLowerThanN(Color.BLUE, 3.1);
        cellStringMap = rule.executeRule(grid);
        assertThat(cellStringMap.size(), is(0));


        rule = new AverageAmountOfCellsInStructureLowerThanN(Color.YELLOW, 3);
        cellStringMap = rule.executeRule(grid);
        assertThat(cellStringMap.size(), is(3));


        rule = new AverageAmountOfCellsInStructureLowerThanN(Color.RED, 3);
        cellStringMap = rule.executeRule(grid);
        assertThat(cellStringMap.size(), is(0));
    }


}