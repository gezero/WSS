package cz.peinlich.exam.color.grid.imlementation;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 8:48
 */
public class ArrayListMatrixGridFactory {
    public ArrayListMatrixGrid buildEmptyMatrixGrid(int width, int height) {
        return new ArrayListMatrixGrid(width, height);
    }
}
