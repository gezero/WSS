package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;
import cz.peinlich.exam.color.structures.implementation.FloodFillAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for creating the grid. It is possible to create empty grid, or a grid from an
 * input stream. Empty cells will have Color.EMPTY color.
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
@Component
public class ArrayListMatrixGridFactory {
    private static final Logger logger = LoggerFactory.getLogger(ArrayListMatrixGridFactory.class);
    private static final int DEFAULT_WIDTH = 5;
    private static final int DEFAULT_HEIGHT = 5;
    /**
     * Pattern for parsing the input file, the first two parts are coordinates, third part is color.
     */
    private static final Pattern pattern = Pattern.compile("(\\d+),(\\d+),([R,G,B,Y])(.*)");

    /**
     * Parses single line of input.
     * @return Returns new Cell object representing single cell in Grid.
     */
    public static Cell parse(String line) {
        Matcher m = pattern.matcher(line);
        logger.debug("Matches: {}", m.matches());
        logger.debug("x:{}", m.group(1));
        logger.debug("y:{}", m.group(2));
        logger.debug("color:{}", m.group(3));
        logger.debug("comment:{}", m.group(4));
        return new CellPojo(new Point(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2))), Color.color(m.group(3)), m.group(4));
    }

    public ArrayListMatrixGrid buildEmptyMatrixGrid(int width, int height) {
        return new ArrayListMatrixGrid("empty", width, height);
    }

    /**
     * Reads grid information from InputStream. Empty lines are ignored. Each line should be of the following format
     * X,Y,C,Comment where X,Y are coordinates, C is color and Comment is free text.
     *
     * After the grid is created, structure calculation is invoked.
     */
    public ArrayListMatrixGrid buildGridFromInputStream(InputStream inputStream, String name) throws IOException {
        ArrayListMatrixGrid grid = new ArrayListMatrixGrid(name, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            logger.info("Reading line: {}", line);
            if (line.trim().equals("")) {
                continue;
            }
            Cell cell = parse(line);
            grid.setCell(cell);
        }

        grid.calculateStructures(new FloodFillAlgorithm());
        return grid;
    }
}
