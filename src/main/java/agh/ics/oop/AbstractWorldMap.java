package agh.ics.oop;

import javax.swing.plaf.multi.MultiOptionPaneUI;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    Map<Vector2d, IMapElement> elementsSet = new HashMap<>();
    MapVisualizer visualizer = new MapVisualizer(this);


    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (!canMoveTo(animalPosition))
            throw new IllegalArgumentException("Can't place the animal at" + animalPosition);
        elementsSet.put(animalPosition, animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return elementsSet.getOrDefault(position, null);
    }

    abstract String generateVisualisation();

    @Override
    public String toString() {
        return generateVisualisation();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = elementsSet.get(oldPosition);
        elementsSet.remove(oldPosition);
        elementsSet.put(newPosition, element);
    }
}
