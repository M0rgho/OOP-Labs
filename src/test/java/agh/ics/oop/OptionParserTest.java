package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OptionParserTest {
    @Test
    public void testParsingInput() {
        assertAll("parsing strings", () -> {
            assertAll("valid strings", () -> {
                assertArrayEquals(new MoveDirection[]{FORWARD, FORWARD},OptionsParser.parse(new String[]{"f", "R", "forward", "Forward", "FORWARD"}));
                assertArrayEquals(new MoveDirection[]{RIGHT, RIGHT},OptionsParser.parse(new String[]{"r", "R", "right", "Right", "RIGHT"}));
                assertArrayEquals(new MoveDirection[]{BACKWARD, BACKWARD},OptionsParser.parse(new String[]{"b", "B", "backward", "Backward", "BACKWARD"}));
                assertArrayEquals(new MoveDirection[]{LEFT, LEFT},OptionsParser.parse(new String[]{"l", "L", "left", "Left", "LEFT"}));
                assertArrayEquals(new MoveDirection[]{FORWARD, BACKWARD}, OptionsParser.parse(new String[]{"f", "b"}));
            });
            assertAll("invalid strings", () -> {
                assertArrayEquals(new MoveDirection[]{}, OptionsParser.parse(new String[]{""}));
                assertArrayEquals(new MoveDirection[]{}, OptionsParser.parse(new String[]{"gdf", "f ", "b ", "l ", "r ", " ", "\\", "? ", "-"}));
                assertArrayEquals(new MoveDirection[]{}, OptionsParser.parse(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
            });
        });
    }
}
