package agh.ics.oop;

class World {
    private static Direction[] processDirections(String[] args) {
        Direction[] directions = new Direction[args.length];
        for(int i = 0; i < args.length; ++i) {
            directions[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.FORWARD;
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
        System.out.println("Animal is moving foward!");
        for(Direction dir : directions) {
            String message = switch (dir) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak skręca w prawo";
                case LEFT -> "Zwierzak skręca w lewo";
            };
            System.out.println(message);
        }
        System.out.println("Stop");
    }
}
