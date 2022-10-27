package agh.ics.oop;

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

//    public String toString(){
//        return switch (this) {
//            case NORTH -> "Północ";
//            case SOUTH -> "Południe";
//            case WEST -> "Zachód";
//            case EAST -> "Wschód";
//        };
//    }

    public static MapDirection next(final MapDirection direction) {
        return switch (direction) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public static MapDirection previous(final MapDirection direction) {
        return switch (direction) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }

    public static Vector2d toUnitVector(final MapDirection direction) {
        return switch (direction) {
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
        };
    }

}