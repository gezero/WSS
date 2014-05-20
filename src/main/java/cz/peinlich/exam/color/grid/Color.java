package cz.peinlich.exam.color.grid;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 9:10
 */
public enum Color {
    RED, GREEN, BLUE, YELLOW, EMPTY;

    public static Color color(String input){
        switch (input){
            case "R":return RED;
            case "G":return GREEN;
            case "B":return BLUE;
            case "Y":return YELLOW;
            default: throw new UnsupportedOperationException("Input color is not defined ("+input+")");
        }
    }
}
