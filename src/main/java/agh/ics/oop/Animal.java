package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;

    private final IWorldMap map;

    Animal(IWorldMap map) {
        this.map = map;
    }
    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        position = initialPosition;
    }

    @Override
    public String toString() {
        return switch (direction) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public boolean isAt(final Vector2d position) {
        return position.x == this.position.x && position.y == this.position.y;
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
                if (map.canMoveTo(newPosition))
                    this.position = newPosition;
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.add(MapDirection.toUnitVector(this.direction).opposite());
                if (map.canMoveTo(newPosition))
                    this.position = newPosition;
            }
        }
    }
}
