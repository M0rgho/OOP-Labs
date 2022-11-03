package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {

    final int width;
    final int height;

    final Vector2d minBoundBox;
    final Vector2d maxBoundBox;
    ArrayList<Animal> animalList;

    RectangularMap(int w, int h) {
        width = w;
        height = h;
        minBoundBox = new Vector2d(0, 0);
        maxBoundBox = new Vector2d(width, height);
        animalList = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follow(minBoundBox) && position.precedes(maxBoundBox) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if(!isOccupied(animalPosition)) {
            animalList.add(animal);
            return true;
        }
        return false;

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal : animalList) {
            if (animal.isAt(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animalList) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return false;
    }
}
