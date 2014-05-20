package cz.peinlich.exam.color.grid.imlementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.grid.Structure;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArrayListMatrixGridTest {

    @Test
    public void createEmptyGrid(){

        ArrayListMatrixGridFactory factory = new ArrayListMatrixGridFactory();

        int width = 25;
        int height = 25;
        ArrayListMatrixGrid grid = factory.buildEmptyMatrixGrid(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = grid.getCell(new Point(i,j));
                assertThat(cell.getColor(),is(Color.EMPTY));
            }
        }

        Collection<Structure> structures = grid.getStructures();
        assertThat(structures.size(),is(0));

    }

}