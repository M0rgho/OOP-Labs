package agh.ics.oop;

import javax.xml.stream.FactoryConfigurationError;

class Vector2d {
    final public int x;
    final public int y;

    Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }

    boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    boolean follow(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    Vector2d oppsite() {
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector2d)) {
            return false;
        }
        return this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
    }

}

class World {
    private static Direction[] processDirections(String[] args) {
        Direction[] directions = new Direction[args.length];
        for(int i = 0; i < args.length; ++i) {
            directions[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> null;
            };
        }
        return directions;
    }

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

//    private static void run(Direction[] directions) {
//        for(Direction dir : directions) {
//            if (dir != null) {
//                String message = switch (dir) {
//                    case FORWARD -> "Zwierzak idzie do przodu";
//                    case BACKWARD -> "Zwierzak idzie do tyłu";
//                    case RIGHT -> "Zwierzak skręca w prawo";
//                    case LEFT -> "Zwierzak skręca w lewo";
//                };
//                System.out.println(message);
//            }
//        }
//        System.out.println("Stop");
//    }
}
