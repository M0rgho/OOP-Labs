package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;

public class AnimalTest {
    Animal animal;
    RectangularMap map = new RectangularMap(10, 10);

    @BeforeEach
    void animalInstantiationTest() {
        animal = new Animal(map);
        assertNotNull(animal);
        assert animal.isAt(new Vector2d(2, 2));
        assert animal.getDirection() == MapDirection.NORTH;
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
        assert animal.isAt(new Vector2d(2, 6));
        animal.move(MoveDirection.LEFT);
        assert animal.isAt(new Vector2d(2, 6));
        animal.move(MoveDirection.FORWARD);
        assert animal.isAt(new Vector2d(1, 6));
    }

}