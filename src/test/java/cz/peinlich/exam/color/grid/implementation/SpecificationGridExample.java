package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.structures.Structure;
import cz.peinlich.exam.color.structures.implementation.FloodFillAlgorithm;
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
 * Time: 10:28
 */
public class SpecificationGridExample {
    ClassPathResource singleCellGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/specification_grid.txt");
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

    @Test
    public void check_that_we_can_create_single_cell_grid() throws IOException {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(singleCellGridInput.getInputStream());
        assertThat(grid, is(notNullValue()));

        Point coordinates = new Point(7, 22);
        Cell cell = grid.getCell(coordinates);
        assertThat(cell.getColor(), is(Color.GREEN));

        grid.calculateStructures(new FloodFillAlgorithm());

        Structure structure = grid.getStructure(coordinates);
        assertThat(structure.getColor(),is(Color.GREEN));
        assertThat(structure.getCells().size(),is(1));

        Structure structureWithNeighbor = grid.getStructure(new Point(11,24));
        assertThat(structureWithNeighbor.getColor(),is(Color.BLUE));

        Collection<Structure> neighborStructures = grid.getNeighborStructures(structureWithNeighbor);
        assertThat(neighborStructures.size(),is(1));
        Structure neighborStructure = neighborStructures.iterator().next();
        assertThat(neighborStructure.getColor(),is(Color.RED));
        assertThat(neighborStructure.getCells().size(),is(2));

    }
}
