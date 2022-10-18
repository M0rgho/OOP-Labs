package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

public class Vector2dTest {
    @Test
    void equalityTest() {
        Object testedVector = new Vector2d(10, 10);
        Object[] notEqualObjects = {"String1", 2, new Vector2d(2,4), new Vector2d(0, 0), 321, new Scanner(System.in), 'c', true, false, 10, new int[]{10, 10}, null};
        Object vec1 = new Vector2d(10, 10);
        Object vecReference = testedVector;
        for(Object obj: notEqualObjects) {
            assertNotEquals(testedVector, obj);
        }
        assertEquals(testedVector, testedVector);
        assertEquals(testedVector, vecReference);
        assertEquals(testedVector, (Object) vecReference);
        assertEquals(testedVector, vec1);
    }

    @Test
    void toStringTest() {
        assertEquals((new Vector2d(1, 2)).toString(), "(1,2)");
        assertEquals((new Vector2d(-432, 0)).toString(), "(-432,0)");
        assertEquals((new Vector2d(-0, 0)).toString(), "(0,0)");
        assertEquals((new Vector2d(111111, 222222)).toString(), "(111111,222222)");
    }

    @Test
    void precedesTest() {
        assertTrue((new Vector2d(0, 0)).precedes(new Vector2d(0, 1)));
        assertTrue((new Vector2d(0, 0)).precedes(new Vector2d(1, 0)));
        assertTrue((new Vector2d(0, 0)).precedes(new Vector2d(0, 0)));
        assertTrue((new Vector2d(-100, 100)).precedes(new Vector2d(100, 200)));

        assertFalse((new Vector2d(0, 0)).precedes(new Vector2d(-1, 0)));
        assertFalse((new Vector2d(0, 0)).precedes(new Vector2d(0, -1)));
    }

    @Test
    void followsTest() {
        assertTrue((new Vector2d(0, 0)).precedes(new Vector2d(0, 1)));
        assertTrue((new Vector2d(0, 0)).precedes(new Vector2d(1, 0)));
        assertTrue((new Vector2d(0, 0)).precedes(new Vector2d(0, 0)));
        assertTrue((new Vector2d(-100, 100)).precedes(new Vector2d(100, 200)));

        assertFalse((new Vector2d(0, 0)).precedes(new Vector2d(-1, 0)));
        assertFalse((new Vector2d(0, 0)).precedes(new Vector2d(0, -1)));
    }

    @Test
    void upperRightTest() {
        assertEquals((new Vector2d(0, 0)).upperRight(new Vector2d(2, 2)), new Vector2d(2, 2));
        assertEquals((new Vector2d(0, 4)).upperRight(new Vector2d(4, 0)), new Vector2d(4, 4));
        assertEquals((new Vector2d(-10, -10)).upperRight(new Vector2d(-100, 0)), new Vector2d(-10, 0));
    }

    @Test
    void lowerLeftTest() {
        assertEquals((new Vector2d(0, 0)).lowerLeft(new Vector2d(2, 2)), new Vector2d(0, 0));
        assertEquals((new Vector2d(0, 4)).lowerLeft(new Vector2d(4, 0)), new Vector2d(0, 0));
        assertEquals((new Vector2d(-10, -10)).lowerLeft(new Vector2d(-100, 0)), new Vector2d(-100, -10));
    }

    @Test
    void additionTest() {
        assertEquals((new Vector2d(6, 8)).add(new Vector2d(6, -5)), new Vector2d(12, 3));
        assertEquals((new Vector2d(6, -23)).add(new Vector2d(0, -12)), new Vector2d(6, -35));
        assertEquals((new Vector2d(5, 3)).add(new Vector2d(3, 5)), new Vector2d(8, 8));
    }

    @Test
    void subtractionTest() {
        assertEquals((new Vector2d(6, 9)).subtract(new Vector2d(5, -5)), new Vector2d(1, 14));
        assertEquals((new Vector2d(6, 12)).subtract(new Vector2d(0, -12)), new Vector2d(6, 24));
        assertEquals((new Vector2d(5, 3)).subtract(new Vector2d(3, 5)), new Vector2d(2, -2));
    }

    @Test
    void opposite() {
        assertEquals((new Vector2d(5, 3)).opposite(), new Vector2d(-5, -3));
        assertEquals((new Vector2d(0, -12)).opposite(), new Vector2d(0, 12));
        assertEquals((new Vector2d(15, -3)).opposite(), new Vector2d(-15, 3));
    }

}
