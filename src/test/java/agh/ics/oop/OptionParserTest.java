package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.*;

public class OptionParserTest {
    @Test
    public void testParsingInput() {
            assertAll("parsing strings", () -> {
            assertAll("valid strings", () -> {
                String[] forwardStrings = new String[] {"f", "forward"};
                String[] rightStrings = new String[] {"r", "right"};
                String[] backwardStrings = new String[] {"b", "backward"};
                String[] leftStrings = new String[] {"l", "left"};

                for (String f : forwardStrings)
                    assertArrayEquals(new MoveDirection[]{FORWARD}, OptionsParser.parse(new String[]{f}));
                for (String f : rightStrings)
                    assertArrayEquals(new MoveDirection[]{RIGHT}, OptionsParser.parse(new String[]{f}));
                for (String f : backwardStrings)
                    assertArrayEquals(new MoveDirection[]{BACKWARD}, OptionsParser.parse(new String[]{f}));
                for (String f : leftStrings)
                    assertArrayEquals(new MoveDirection[]{LEFT}, OptionsParser.parse(new String[]{f}));
            });
            assertAll("invalid strings", () -> {
                String[] invalidStrings = new String[]{"gdf", "f ", "b ", "l ", "r ", " ", "\\", "? ", "-", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "true"};
                String[] InvalidCaseSensitive = new String[] {
                        "F", "forWarD", "Forward", "FORWARD",
                        "R", "RiGht", "Right", "RIGHT",
                        "B", "Backward", "bacKwaRd", "BACKWARD",
                        "L", "Left", "leFT", "LEFT"};
                for (String badStr : invalidStrings) {
                    assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(new String[]{badStr}));
                }
                for (String badStr : InvalidCaseSensitive) {
                    assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(new String[]{badStr}));
                }
                });
        });
    }
}
