package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] directions;
    private final RectangularMap map;
    private final Vector2d[] positions;

    SimulationEngine(MoveDirection[] directions, RectangularMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    @Override
    public void run() {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            map.place(animal);
        }
        for(MoveDirection moveDir : directions) {
            map.getNextAnimal().move(moveDir);
        }
    }
}
