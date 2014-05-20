package cz.peinlich.exam.color.structures.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGrid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.structures.Structure;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class FloodFillAlgorithmTest {
    ClassPathResource singleCellGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/single_cell_grid.txt");
    ClassPathResource specificationGridInput = new ClassPathResource("cz/peinlich/exam/color/grid/implementation/specification_grid.txt");
    ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

    @Test
    public void find_single_structure() throws Exception {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(singleCellGridInput.getInputStream());
        assertThat(grid, is(notNullValue()));

        FloodFillAlgorithm algorithm = new FloodFillAlgorithm();

        Map<Cell, Structure> structures = algorithm.findStructures(grid);
        assertThat(structures.size(),is(1));

        Structure structure = structures.get(grid.getCell(new Point(7,22)));
        assertThat(structure.getCells().size(), is(1));

        Cell cell = structure.getCells().iterator().next();
        assertThat(cell.getColor(),is(Color.GREEN));
        assertThat(cell.getCoordinates().getX(),is(7));
        assertThat(cell.getCoordinates().getY(),is(22));

    }

    @Test
    public void find_structures_from_specification() throws Exception {
        ArrayListMatrixGrid grid = factory.buildGridFromInputStream(specificationGridInput.getInputStream());
        assertThat(grid, is(notNullValue()));

        FloodFillAlgorithm algorithm = new FloodFillAlgorithm();

        Map<Cell, Structure> structures = algorithm.findStructures(grid);
        Structure structure = structures.get(grid.getCell(new Point(7,22)));

        Cell cell = structure.getCells().iterator().next();
        assertThat(cell.getColor(),is(Color.GREEN));
        assertThat(cell.getCoordinates().getX(),is(7));
        assertThat(cell.getCoordinates().getY(),is(22));

    }
}