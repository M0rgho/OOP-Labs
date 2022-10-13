package agh.ics.oop;

enum Direction {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT
}

enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT
}

enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public String toString(){
        return switch (this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case WEST -> "Południe";
            case EAST -> "Południe";
        };
    }

    public MapDirection next(MapDirection direction) {
        return switch (direction) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }

    public MapDirection previous(MapDirection direction) {
        return switch (direction) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
    public Vector2d toUnitVector(MapDirection direction) {
        return switch (direction) {
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
        };
    }

}