package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 9:32
 */
public class SingleCellGridTest {
    ClassPathResource singleCellGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/single_cell_grid.txt");
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

    @Test
    public void check_that_we_can_create_single_cell_grid() throws IOException {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(singleCellGridInput.getInputStream(), null);
        assertThat(grid, is(notNullValue()));

        Cell cell = grid.getCell(new Point(7, 22));
        assertThat(cell.getColor(), is(Color.GREEN));

        Collection<Cell> nonEmptyCells = grid.getNonEmptyCells();
        assertThat(nonEmptyCells.size(),is(1));
        Cell storedCell = nonEmptyCells.iterator().next();
        assertThat(storedCell,is(cell));
    }
}
