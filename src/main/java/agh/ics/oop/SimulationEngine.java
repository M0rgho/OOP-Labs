package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private final List<Animal> animals = new ArrayList<>();
    private final MoveDirection[] directions;
    private final AbstractWorldMap map;
    private final Vector2d[] positions;
    private int moveDelay = 0;

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



    @Override
    public void run() {
        for (int i = 0; i < directions.length; ++i) {
            animals.get(i % animals.size()).move(directions[i]);
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                System.err.println("Thread execution was abrupted");
            }
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        for (Animal animal : animals) {
            animal.addObserver(observer);
        }
    }
}
