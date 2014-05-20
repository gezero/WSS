package cz.peinlich.exam.color.grid;

import org.junit.Test;

public class ColorTest {

    @Test(expected = RuntimeException.class)
    public void testException() {
        Color.color("X");
    }

}