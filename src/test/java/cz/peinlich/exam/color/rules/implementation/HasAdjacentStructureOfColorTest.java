package cz.peinlich.exam.color.rules.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGrid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.rules.implementation.rules.HasAdjacentStructureOfColor;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HasAdjacentStructureOfColorTest {
    ClassPathResource singleCellGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/specification_grid.txt");
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

    @Test
    public void test_blue_red_neighbors_from_specification_grid() throws IOException {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(singleCellGridInput.getInputStream(), null);
        assertThat(grid, is(notNullValue()));

        HasAdjacentStructureOfColor rule = new HasAdjacentStructureOfColor(Color.BLUE,Color.RED);
        Map<Cell, String> cellStringMap = rule.executeRule(grid);
        assertThat(cellStringMap.size(),is(2));
    }

}