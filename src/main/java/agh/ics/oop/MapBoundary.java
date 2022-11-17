package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    private final AbstractWorldMap thisMap;
    private final SortedSet<Integer> xCoordinates = new TreeSet<>();
    private final SortedSet<Integer> yCoordinates = new TreeSet<>();

    public MapBoundary(AbstractWorldMap thisMap) {
        this.thisMap = thisMap;
        for(Vector2d position : thisMap.elementsSet.keySet()) {
            xCoordinates.add(position.x);
            yCoordinates.add(position.y);
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xCoordinates.remove(oldPosition.x);
        yCoordinates.remove(oldPosition.y);
        xCoordinates.add(newPosition.x);
        yCoordinates.add(newPosition.y);
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(xCoordinates.first(), yCoordinates.first());
    }

    public Vector2d getUpperRight() {
        return new Vector2d(xCoordinates.last(), yCoordinates.last());
    }
}
