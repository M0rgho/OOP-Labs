package agh.ics.oop;

import java.awt.desktop.PreferencesEvent;
import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap{
    ArrayList<IMapElement> elementsList = new ArrayList<>();
    MapVisualizer visualizer = new MapVisualizer(this);


    @Override
    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition()))
            return false;
        elementsList.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(IMapElement element: elementsList) {
            if (position.equals(element.getPosition())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(IMapElement element: elementsList) {
            if (position.equals(element.getPosition())) {
                return element;
            }
        }
        return null;
    }

    abstract String generateVisualisation();

    @Override
    public String toString() {
        return generateVisualisation();
    }
}
