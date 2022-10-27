package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.*;

public class AnimalTest {



    Animal animal;

    @BeforeEach
    void animalInstantiationTest() {
        animal = new Animal();
        assertNotNull(animal);
        assert animal.isAt(new Vector2d(2, 2));
        assert animal.getDirection() == MapDirection.NORTH;
    }

    @AfterEach
    void assertValidPosition() {
        assert animal.areCoordinatesValid(animal.getPosition());
    }

    @Test
    @DisplayName("Manual movement and turning test")
    void MovementTest(){
        for(int i = 0; i < 4; ++i) {
            animal.move(MoveDirection.LEFT);
        }
        assert animal.getDirection() == MapDirection.NORTH;
        assert animal.isAt(new Vector2d(2, 2));
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assert animal.isAt(new Vector2d(2, 4));
        animal.move(MoveDirection.LEFT);
        assert animal.isAt(new Vector2d(2, 4));
        animal.move(MoveDirection.FORWARD);
        assert animal.isAt(new Vector2d(1, 4));
    }

    @Test
    @DisplayName("Tests for main arguments")
    public void testMainArgs() {
        System.out.println("main");
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true));
        World.main(new String[]{"f", "f", "f"});
        assertEquals("Position: (2, 4) Orientation: NORTH\r\n", outContent.toString());

        outContent.reset();
        World.main(new String[]{"fds", "dfg", "dfgdfdf" , "l", "left"});
        assertEquals("Position: (2, 2) Orientation: SOUTH\r\n", outContent.toString());

        outContent.reset();
        World.main(new String[]{"b", "b", "-132", "r"});
        assertEquals("Position: (2, 0) Orientation: EAST\r\n", outContent.toString());

        outContent.reset();
        World.main(new String[]{"forward", "right", "backward", "left"});
        assertEquals("Position: (1, 3) Orientation: NORTH\r\n", outContent.toString());

    }
//    static MoveDirection oppositeMove(MoveDirection move) {
//        return switch (move) {
//            case FORWARD -> MoveDirection.BACKWARD;
//            case RIGHT -> MoveDirection.LEFT;
//            case LEFT -> MoveDirection.RIGHT;
//            case BACKWARD -> MoveDirection.FORWARD;
//        };
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {5, 10, 20, 50, 100})
//    @DisplayName("Test random walk and a its reverse")
//    void randomMovementTest(final int n) {
//        MoveDirection[] moves = genereteMoves(n);
//        for(MoveDirection move : moves) {
//            System.out.print(move.name());
//            animal.move(move);
//        }
//        for(int i = moves.length - 1; i >= 0; --i) {
//            animal.move(oppositeMove(moves[i]));
//        }
//        assert animal.isAt(new Vector2d(2,  2));
//        assert animal.getDirection() == MapDirection.NORTH;
//    }
}
