package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;



public class AnimalTest {
    Animal animal;
    Vector2d startingPosition = new Vector2d(50, 50);
    // Test map that always allows animal movement;
    AbstractWorldMap map = new AbstractWorldMap() {
        @Override
        public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

        }

        @Override
        String generateVisualisation() {
            return "";
        }
        @Override
        public boolean canMoveTo(Vector2d position) {
            return true;
        }
    };

    @BeforeEach
    void animalInstantiationTest() {
        animal = new Animal(map, startingPosition);
        assertNotNull(animal);
        assert animal.isAt(startingPosition);
        assert animal.getDirection() == MapDirection.NORTH;
    }

    @Test
    @DisplayName("Turning test")
    void rotationTest(){
        for(int i = 0; i < 40; ++i) {
            animal.move(MoveDirection.LEFT);
        }
        assert animal.getDirection() == MapDirection.NORTH;
        assert animal.getPosition() == startingPosition;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 100})
    @DisplayName("Forward movement test")
    void forwardTest(final int n){
        for(int i = 0; i < n; ++i) {
            animal.move(MoveDirection.FORWARD);
        }
        assert animal.getDirection() == MapDirection.NORTH;
        assert animal.getPosition().equals(new Vector2d(startingPosition.x, startingPosition.y + n));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 100})
    @DisplayName("Backward movement test")
    void backwardTest(final int n){
        for(int i = 0; i < n; ++i) {
            animal.move(MoveDirection.BACKWARD);
        }
        assert animal.getDirection() == MapDirection.NORTH;
        assert animal.getPosition().equals(new Vector2d(startingPosition.x, startingPosition.y - n));
    }



    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 50})
    @DisplayName("Test random walk and a its reverse")
    void randomMovementTest(final int n) {
        MovesGenerator generator = new MovesGenerator(1000);
        MoveDirection[] moves = generator.generateMoves(n);
        for(MoveDirection move : moves) {
            animal.move(move);
        }
        for(int i = moves.length - 1; i >= 0; --i) {
            animal.move(MovesGenerator.oppositeMove(moves[i]));
        }
        assert animal.isAt(startingPosition);
        assert animal.getDirection() == MapDirection.NORTH;
    }
}
