package agh.ics.oop;

import javax.xml.stream.FactoryConfigurationError;

class Vector2d {
    final public int x;
    final public int y;

    Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public boolean equals(Object other) {
        if (!(other instanceof Vector2d)) {
            return false;
        }
        return this.x == other.x && this.y == other.y;
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
        System.out.println("system wystartował");
        run(processDirections(args));
        System.out.println("system zakończył działanie");
    }

    private static void run(Direction[] directions) {
        for(Direction dir : directions) {
            if (dir != null) {
                String message = switch (dir) {
                    case FORWARD -> "Zwierzak idzie do przodu";
                    case BACKWARD -> "Zwierzak idzie do tyłu";
                    case RIGHT -> "Zwierzak skręca w prawo";
                    case LEFT -> "Zwierzak skręca w lewo";
                };
                System.out.println(message);
            }
        }
        System.out.println("Stop");
    }
}
