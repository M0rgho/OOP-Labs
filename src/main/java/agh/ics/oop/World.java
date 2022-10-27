package agh.ics.oop;

class World {


    public static void main(String[] args) {
        int h = 5;
        int w = 10;

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(w, h);
        MapVisualizer mapVisualizer = new MapVisualizer(map);

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        String visualisedMap = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(w, h));
        System.out.println(visualisedMap);
    }

}
