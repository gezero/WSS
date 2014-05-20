package cz.peinlich.exam.color.structures.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGrid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.structures.Structure;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class FloodFillAlgorithmTest {
    ClassPathResource singleCellGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/single_cell_grid.txt");
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

    @Test
    public void find_single_structure() throws Exception {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(singleCellGridInput.getInputStream());
        assertThat(grid, is(notNullValue()));

        FloodFillAlgorithm algorithm = new FloodFillAlgorithm();

        Collection<Structure> structures = algorithm.findStructures(grid);
        assertThat(structures.size(),is(1));

        Structure structure = structures.iterator().next();
        assertThat(structure.getCells().size(), is(1));

        Cell cell = structure.getCells().iterator().next();
        assertThat(cell.getColor(),is(Color.GREEN));
        assertThat(cell.getCoordinates().getX(),is(7));
        assertThat(cell.getCoordinates().getY(),is(22));

    }
}