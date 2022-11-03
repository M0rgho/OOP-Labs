package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class SimulationEngineTest {

    @Test
    void engineTest() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] moves = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(5, 10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(moves, map, positions);
        engine.run();
        MapVisualizer visualizer = new MapVisualizer(map);
        System.out.println(visualizer.draw(new Vector2d(0, 0), new Vector2d(5, 10)));
        assert map.isOccupied(new Vector2d(2, 0));
        assert map.isOccupied(new Vector2d(3, 7));
    }

    @Test
    void engineCollisionTest() {
        String[] args = {"f", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] moves = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(5, 10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3) };
        SimulationEngine engine = new SimulationEngine(moves, map, positions);
        engine.run();
//        MapVisualizer visualizer = new MapVisualizer(map);
//        System.out.println(visualizer.draw(new Vector2d(0, 0), new Vector2d(5, 10)));
        assert map.isOccupied(new Vector2d(2, 7));
        assert map.isOccupied(new Vector2d(2, 6));
    }
}
