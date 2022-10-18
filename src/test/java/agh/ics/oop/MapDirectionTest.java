package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapDirectionTest {
    @Test
    void checkMapOrder() {
        MapDirection[] directions = MapDirection.values();
        for (MapDirection dir : directions) {
            assert MapDirection.next(MapDirection.previous(dir)) == dir;
        }
    }
    @Test
    void checkMapNext() {
        MapDirection[] directions = MapDirection.values();
        for (MapDirection dir : directions) {
            MapDirection correctDirection = switch (dir) {
                case NORTH -> MapDirection.WEST;
                case EAST -> MapDirection.NORTH;
                case SOUTH -> MapDirection.EAST;
                case WEST -> MapDirection.SOUTH;
            };
            assert MapDirection.next(dir) == correctDirection;
        }
    }

    @Test
    void checkMapPrevious() {
        MapDirection[] directions = MapDirection.values();
        for (MapDirection dir : directions) {
            MapDirection correctDirection = switch (dir) {
                case NORTH -> MapDirection.EAST;
                case EAST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.WEST;
                case WEST -> MapDirection.NORTH;
            };
            assert MapDirection.previous(dir) == correctDirection;
        }
    }
}
