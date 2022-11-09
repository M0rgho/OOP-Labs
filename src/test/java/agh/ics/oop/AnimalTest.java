package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Random;



public class AnimalTest {
    Animal animal;
    RectangularMap map = new RectangularMap(100, 100);

    @BeforeEach
    void animalInstantiationTest() {
        animal = new Animal(map, new Vector2d(50, 50));
        assertNotNull(animal);
        assert animal.isAt(new Vector2d(50, 50));
        assert animal.getDirection() == MapDirection.NORTH;
    }

    @Test
    @DisplayName("Manual movement and turning test")
    void RotationTest(){
        for(int i = 0; i < 4; ++i) {
            animal.move(MoveDirection.LEFT);
        }
        assert animal.getDirection() == MapDirection.NORTH;
    }

    static MoveDirection oppositeMove(MoveDirection move) {
        return switch (move) {
            case FORWARD -> MoveDirection.BACKWARD;
            case RIGHT -> MoveDirection.LEFT;
            case LEFT -> MoveDirection.RIGHT;
            case BACKWARD -> MoveDirection.FORWARD;
        };
    }
    static MoveDirection[] generateMoves(final int n) {
        Random rand = new Random(1000);
        MoveDirection[] allMoves = MoveDirection.values();
        MoveDirection[] generatedMoves = new MoveDirection[n];
        for (int i = 0; i < n; ++i) {
            generatedMoves[i] = allMoves[rand.nextInt(0, MoveDirection.values().length)];
        }
        return generatedMoves;
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 50})
    @DisplayName("Test random walk and a its reverse")
    void randomMovementTest(final int n) {
        MoveDirection[] moves = generateMoves(n);
        for(MoveDirection move : moves) {
            animal.move(move);
        }
        for(int i = moves.length - 1; i >= 0; --i) {
            animal.move(oppositeMove(moves[i]));
        }
        assert animal.isAt(new Vector2d(50,  50));
        assert animal.getDirection() == MapDirection.NORTH;
    }
}
