package agh.ics.oop;

import java.util.ArrayList;

public class Animal implements IMapElement{
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;

    ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
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
        return this.position.equals(position);
    }

    public MapDirection getDirection() {
        return direction;
    }
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getGraphicRepresentation() {
        return switch (direction) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case EAST -> "src/main/resources/right.png";
            case WEST -> "src/main/resources/left.png";
        };
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.direction = MapDirection.next(this.direction);
            case LEFT -> this.direction = MapDirection.previous(this.direction);
            case FORWARD -> {
                Vector2d newPosition = this.position.add(MapDirection.toUnitVector(this.direction));
                if (map.canMoveTo(newPosition))
                    changePosition(newPosition);
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.add(MapDirection.toUnitVector(this.direction).opposite());
                if (map.canMoveTo(newPosition))
                    changePosition(newPosition);
            }
        }
    }
    void changePosition(Vector2d newPosition) {
        positionChanged(this.position, newPosition);
        this.position = newPosition;
    }


    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}
