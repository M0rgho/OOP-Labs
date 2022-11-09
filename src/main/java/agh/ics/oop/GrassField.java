package agh.ics.oop;

import javax.swing.text.Position;
import java.util.Arrays;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    final int grassCount;
    final int grassUpperBound;
    private final Random random = new Random();

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        grassUpperBound = (int) (Math.sqrt(grassCount*10)) + 1;
        generateGrass();
    }

    public GrassField(int grassCount, long seed) {
        random.setSeed(seed);
        this.grassCount = grassCount;
        grassUpperBound = (int) (Math.sqrt(grassCount*10)) + 1;
        generateGrass();
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
            elementsList.add(new Grass(newPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object element = objectAt(position);
        if (element instanceof Grass) {
            ((Grass) element).position = getRandomEmptyField();
        }
        return element == null || element instanceof Grass;
    }

    @Override
    String generateVisualisation() {
        Vector2d lowerLeft = new Vector2d(grassUpperBound, grassUpperBound);
        Vector2d upperRight = new Vector2d(0, 0);
        for(IMapElement element : elementsList) {
            Vector2d position = element.getPosition();
            lowerLeft = lowerLeft.lowerLeft(position);
            upperRight = upperRight.upperRight(position);
        }
        return visualizer.draw(lowerLeft, upperRight);
    }
}
