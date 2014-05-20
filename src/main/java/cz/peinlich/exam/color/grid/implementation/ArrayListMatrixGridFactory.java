package cz.peinlich.exam.color.grid.implementation;

import cz.peinlich.exam.color.grid.Cell;
import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
public class ArrayListMatrixGridFactory {
    private static final Logger logger = LoggerFactory.getLogger(ArrayListMatrixGridFactory.class);
    private static final int DEFAULT_WIDTH = 5;
    private static final int DEFAULT_HEIGHT = 5;
    /**
     * Patern for parsing the input file, the first two parts are coordinates, third part is color:
     */
    private static final Pattern pattern = Pattern.compile("(\\d+),(\\d+),([R,G,B,Y]).*");

    public static Cell parse(String line) {
        Matcher m = pattern.matcher(line);
        logger.debug("Matches: {}", m.matches());
        logger.debug("x:{}", m.group(1));
        logger.debug("y:{}", m.group(2));
        logger.debug("color:{}", m.group(3));
        return new CellPojo(new Point(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2))), Color.color(m.group(3)));
    }

    public ArrayListMatrixGrid buildEmptyMatrixGrid(int width, int height) {
        return new ArrayListMatrixGrid(width, height);
    }

    public ArrayListMatrixGrid buildGridFromInputStream(InputStream inputStream) throws IOException {
        ArrayListMatrixGrid grid = new ArrayListMatrixGrid(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            logger.info("Reading line: {}", line);
            Cell cell = parse(line);
            grid.setCell(cell);
        }
        return grid;
    }
}
