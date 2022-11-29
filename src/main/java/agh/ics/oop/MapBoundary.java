package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    private final AbstractWorldMap thisMap;
    private final SortedSet<Vector2d> xCoordinates = new TreeSet<>(Comparator.comparingInt(o -> o.x));
    private final SortedSet<Vector2d> yCoordinates = new TreeSet<>(Comparator.comparingInt(o -> o.y));

    public MapBoundary(AbstractWorldMap thisMap) {
        this.thisMap = thisMap;
        for(Vector2d position : thisMap.elementsSet.keySet()) {
            xCoordinates.add(position);
            yCoordinates.add(position);
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xCoordinates.remove(oldPosition);
        yCoordinates.remove(oldPosition);
        xCoordinates.add(newPosition);
        yCoordinates.add(newPosition);
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(xCoordinates.first().x, yCoordinates.first().y);
    }

    public Vector2d getUpperRight() {
        return new Vector2d(xCoordinates.last().x, yCoordinates.last().y);
    }

    public void addElement(Vector2d animalPosition) {
        xCoordinates.add(animalPosition);
        yCoordinates.add(animalPosition);
    }
}
