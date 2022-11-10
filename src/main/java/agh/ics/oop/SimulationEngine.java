package agh.ics.oop;

public class SimulationEngine implements IEngine {
    private final Animal[] animals;
    private final MoveDirection[] directions;
    private final AbstractWorldMap map;
    private final Vector2d[] positions;

    SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        animals = new Animal[positions.length];
    }

    @Override
    public void run() {
        for (int i = 0; i < positions.length; ++i) {
            Animal animal = new Animal(map, positions[i]);
            animals[i] = animal;
            animal.addObserver(map);
            if(!map.place(animal))
                throw new IllegalArgumentException("Can't place the animal at" + positions[i]);
        }
        for (int i = 0; i < directions.length; ++i) {
            animals[i % animals.length].move(directions[i]);
        }
    }
}
