package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {
    final List<Animal> animals = new ArrayList<>();
    MoveDirection[] directions;
    final AbstractWorldMap map;
    final Vector2d[] positions;

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        for (int i = 0; i < positions.length; ++i) {
            Animal animal = new Animal(map, positions[i]);
            animals.add(animal);
            map.place(animal);
        }
        addObserver(map);
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.length; ++i) {
            animals.get(i % animals.size()).move(directions[i]);
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        for (Animal animal : animals) {
            animal.addObserver(observer);
        }
    }
}
