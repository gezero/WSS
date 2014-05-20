package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class EmptyGridTests {
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();
    ClassPathResource emptyGridCell = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/empty_grid.txt");

    @Test
    public void create_empty_grid_of_fixed_size() {

        int width = 25;
        int height = 25;
        ArrayListMatrixGrid grid = factory.buildEmptyMatrixGrid(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = grid.getCell(new Point(i, j));
                assertThat(cell.getColor(), is(Color.EMPTY));
            }
        }
        assertThat(grid.getName(),is("empty"));
    }

    @Test
    public void create_grid_from_external_resource() throws IOException {

        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(emptyGridCell.getInputStream(), "name");
        assertThat(grid, is(notNullValue()));
        assertThat(grid.getName(),is("name"));


    }

}