package cz.peinlich.exam.color.grid.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
public class ArrayListMatrixGridFactory {
    private static final Logger logger = LoggerFactory.getLogger(ArrayListMatrixGridFactory.class);
    private static final int DEFAULT_WIDTH = 25;
    private static final int DEFAULT_HEIGHT = 25;

    public ArrayListMatrixGrid buildEmptyMatrixGrid(int width, int height) {
        return new ArrayListMatrixGrid(width, height);
    }

    public ArrayListMatrixGrid buildGridFromInputStream(InputStream inputStream) throws IOException {
        ArrayListMatrixGrid grid = new ArrayListMatrixGrid(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line=reader.readLine())!=null){
            logger.info("Reading line: {}",line);
            throw new RuntimeException("todo:");
        }
        return grid;
    }
}
