package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] parse(final String[] moves) {
        ArrayList<MoveDirection> directions = new ArrayList<MoveDirection>();
        for(String move : moves) {
            switch (move) {
                case "f", "forward" -> directions.add(MoveDirection.FORWARD);
                case "r", "right" -> directions.add(MoveDirection.RIGHT);
                case "b", "backward" -> directions.add(MoveDirection.BACKWARD);
                case "l", "left" -> directions.add(MoveDirection.LEFT);
            }
        }
        return directions.toArray(new MoveDirection[0]);
    }
}