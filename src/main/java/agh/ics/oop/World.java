package agh.ics.oop;

class World {


    public static void main(String[] args) {
        int grassCount = 10;

        MoveDirection[] directions = OptionsParser.parse(args);
        AbstractWorldMap map = new GrassField(grassCount);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        System.out.println(map);


    }

}
