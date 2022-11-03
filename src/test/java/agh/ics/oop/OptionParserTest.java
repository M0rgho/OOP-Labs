package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OptionParserTest {

    @Test
    void parsingTest() {
        assertArrayEquals(OptionsParser.parse(new String[] {"f"}), new MoveDirection[]{MoveDirection.FORWARD});
        assertArrayEquals(OptionsParser.parse(new String[] {"forward"}), new MoveDirection[]{MoveDirection.FORWARD});
        assertArrayEquals(OptionsParser.parse(new String[] {"r"}), new MoveDirection[]{MoveDirection.RIGHT});
        assertArrayEquals(OptionsParser.parse(new String[] {"right"}), new MoveDirection[]{MoveDirection.RIGHT});
        assertArrayEquals(OptionsParser.parse(new String[] {"b"}), new MoveDirection[]{MoveDirection.BACKWARD});
        assertArrayEquals(OptionsParser.parse(new String[] {"backward"}), new MoveDirection[]{MoveDirection.BACKWARD});
        assertArrayEquals(OptionsParser.parse(new String[] {"l"}), new MoveDirection[]{MoveDirection.LEFT});
        assertArrayEquals(OptionsParser.parse(new String[] {"left"}), new MoveDirection[]{MoveDirection.LEFT});

        String[] wrongArgs = {"q", "g", " ", "?", ".", "fff", "bb", "dfa", "0"};
        assert Arrays.equals(OptionsParser.parse(wrongArgs), new MoveDirection[]{});
    }
}
