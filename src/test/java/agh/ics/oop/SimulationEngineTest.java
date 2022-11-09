package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class SimulationEngineTest {

    @Test
    void testEngine() {
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assert map.isOccupied(new Vector2d(3, 5));
        assert map.isOccupied(new Vector2d(2, 0 ));
    }

    @Test
    void testAnimalCollisions() {
        MoveDirection[] directions = OptionsParser.parse(new String[]{
                "f", "r", "r", "r", "r",
                "r", "l", "l", "l", "l",
                "f", "r", "r", "r", "r",
                "f", "r", "r", "r", "r",
        });
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(0,2), new Vector2d(0,1), new Vector2d(1,1), new Vector2d(0,3), new Vector2d(1,3)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assert map.isOccupied(new Vector2d(0, 1));
        assert map.isOccupied(new Vector2d(1, 1));
        assert map.isOccupied(new Vector2d(0, 3));
        assert map.isOccupied(new Vector2d(1, 3));
        assert map.isOccupied(new Vector2d(2, 2));
    }
}
