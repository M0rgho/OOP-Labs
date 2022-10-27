package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;

    SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }



    @Override
    public void run() {
        List<Animal> animals = new ArrayList<>();
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            animals.add(animal);
            map.place(animal);
        }
        for (int i = 0; i < directions.length; ++i) {
            Animal curAnimal = animals.get(i % animals.size());
            curAnimal.move(directions[i]);
        }
    }
}
