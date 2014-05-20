package cz.peinlich.exam.color.grid;

/**
 * Represents color of a cell in a grid
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 9:10
 */
public enum Color {
    RED, GREEN, BLUE, YELLOW, EMPTY;

    public static Color color(String input) {
        switch (input) {
            case "R":
                return RED;
            case "G":
                return GREEN;
            case "B":
                return BLUE;
            case "Y":
                return YELLOW;
            default:
                throw new UnsupportedOperationException("Input color is not defined (" + input + ")");
        }
    }
}
