package cz.peinlich.exam.color.grid;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 9:10
 */
public enum Color {
    GREEN, EMPTY;

    public static Color color(String input){
        switch (input){
            case "G":return GREEN;
            default: throw new UnsupportedOperationException("Input color is not defined ("+input+")");
        }
    }
}
