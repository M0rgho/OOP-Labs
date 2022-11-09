package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap {

    final int width;
    final int height;

    final Vector2d minBoundBox;
    final Vector2d maxBoundBox;

    RectangularMap(int w, int h) {
        width = w;
        height = h;
        minBoundBox = new Vector2d(0, 0);
        maxBoundBox = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follow(minBoundBox) && position.precedes(maxBoundBox) && !isOccupied(position);
    }

    @Override
    String generateVisualisation() {
        return visualizer.draw(minBoundBox, maxBoundBox);
    }

}
