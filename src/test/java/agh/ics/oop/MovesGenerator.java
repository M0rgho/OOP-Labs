package agh.ics.oop;

import java.util.Random;

public class MovesGenerator {
    Random random;

    public MovesGenerator(){
        random = new Random();
    }

    public MovesGenerator(long seed){
        random = new Random(seed);
    }

    public static MoveDirection oppositeMove(MoveDirection move) {
        return switch (move) {
            case FORWARD -> MoveDirection.BACKWARD;
            case RIGHT -> MoveDirection.LEFT;
            case LEFT -> MoveDirection.RIGHT;
            case BACKWARD -> MoveDirection.FORWARD;
        };
    }
    public MoveDirection[] generateMoves(final int n) {
        MoveDirection[] allMoves = MoveDirection.values();
        MoveDirection[] generatedMoves = new MoveDirection[n];
        for (int i = 0; i < n; ++i) {
            generatedMoves[i] = allMoves[random.nextInt(0, MoveDirection.values().length)];
        }
        return generatedMoves;
    }
}
