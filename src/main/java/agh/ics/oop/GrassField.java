package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap{
    final int grassCount;
    final int grassUpperBound;
    private final Random random = new Random();

    public final MapBoundary mapBoundary;

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        grassUpperBound = (int) (Math.sqrt(grassCount*10)) + 1;
        generateGrass();
        mapBoundary = new MapBoundary(this);
    }


    private Vector2d getRandomEmptyField() {
        Vector2d newPosition = new Vector2d(random.nextInt(0, grassUpperBound), random.nextInt(0, grassUpperBound));
        while (isOccupied(newPosition)) {
            newPosition = new Vector2d(random.nextInt(0, grassUpperBound), random.nextInt(0, grassUpperBound));
        }
        return newPosition;
    }

    void generateGrass() {
        for(int i = 0; i < grassCount; ++i) {
            Vector2d newPosition = getRandomEmptyField();
            elementsSet.put(newPosition, new Grass(newPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object element = objectAt(position);
        if (element instanceof Grass) {
            relocateGrass((Grass) element);
        }
        return element == null || element instanceof Grass;
    }

    private void relocateGrass(Grass grass) {
        Vector2d newPosition = getRandomEmptyField();
        positionChanged(grass.position, newPosition);
        mapBoundary.positionChanged(grass.position, newPosition);
        grass.position = newPosition;
    }

    @Override
    String generateVisualisation() {
        return visualizer.draw(mapBoundary.getLowerLeft(), mapBoundary.getUpperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        mapBoundary.positionChanged(oldPosition, newPosition);
        IMapElement element = elementsSet.get(oldPosition);
        elementsSet.remove(oldPosition);
        elementsSet.put(newPosition, element);
    }

}
