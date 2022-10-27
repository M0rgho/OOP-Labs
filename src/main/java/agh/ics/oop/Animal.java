package agh.ics.oop;

public class Animal {

    static boolean[][] isAnimalThere = new boolean[5][5];
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;

    final Vector2d boundingBoxMin = new Vector2d(0, 0);
    final Vector2d boundingBoxMax = new Vector2d(4,4);

    @Override
    public String toString() {
        return String.format("Position: (%d, %d) Orientation: %s", this.position.x, this.position.y, this.direction);
    }

    public boolean isAt(final Vector2d position) {
        return position.x == this.position.x && position.y == this.position.y;
    }

    boolean areCoordinatesValid(final Vector2d coords) {
        return boundingBoxMin.precedes(coords) && boundingBoxMax.follow(coords);
    }

    public MapDirection getDirection() {
        return direction;
    }
    public Vector2d getPosition() {
        return position;
    }


    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.direction = MapDirection.next(this.direction);
            case LEFT -> this.direction = MapDirection.previous(this.direction);
            case FORWARD -> {
                Vector2d newPosition = this.position.add(MapDirection.toUnitVector(this.direction));
                if (areCoordinatesValid(newPosition))
                    this.position = newPosition;
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.add(MapDirection.toUnitVector(this.direction).opposite());
                if (areCoordinatesValid(newPosition))
                    this.position = newPosition;
            }
        }
    }
}
