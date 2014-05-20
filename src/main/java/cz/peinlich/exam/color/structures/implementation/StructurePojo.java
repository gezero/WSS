package cz.peinlich.exam.color.structures.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.structures.Structure;

import java.util.Collection;
import java.util.List;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 11:24
 */
public class StructurePojo implements Structure {
    private List<Cell> cells;
    private Color color;

    public StructurePojo(Color color, List<Cell> cells) {
        this.color = color;
        this.cells = cells;
    }

    @Override
    public Collection<Cell> getCells() {
        return cells;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
