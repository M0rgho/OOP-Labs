package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x, int y) {
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

    Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector2d)) {
            return false;
        }
        return this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

}
