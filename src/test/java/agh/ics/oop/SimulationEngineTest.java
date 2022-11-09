package agh.ics.oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

public class SimulationEngineTest {

    @Test
    void testEngine() {
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assert map.isOccupied(new Vector2d(3, 5));
        assert map.isOccupied(new Vector2d(2, 0));
    }

    @Test
    @DisplayName("Animal collision test")
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

    @ParameterizedTest
    @ValueSource(ints = {2, 15, 50, 100})
    @DisplayName("Tests Rectangular Map boundaries")
    void testRectangularMap(final int n) {
        MovesGenerator generator = new MovesGenerator(2022);

        RectangularMap map = new RectangularMap(n, n);
        Vector2d[] positions = {new Vector2d(0,0)};
        MoveDirection[] directions = generator.generateMoves(100);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        for(IMapElement element : map.elementsList) {
            Vector2d animalPosition = element.getPosition();
            assert animalPosition.follow(new Vector2d(0, 0)) && animalPosition.precedes(new Vector2d(n, n));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 15, 50, 100})
    @DisplayName("Tests grass displacement by animals")
    void testGrassField(final int n) {
        MovesGenerator generator = new MovesGenerator(2022);

        GrassField map = new GrassField(n);
        Vector2d[] positions = {new Vector2d(0,0)};
        MoveDirection[] directions = generator.generateMoves(100);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assert map.elementsList.size() == n + positions.length;
        for(IMapElement element : map.elementsList) {
            if (element instanceof Grass) {
                Vector2d grassPosition = element.getPosition();
                assert grassPosition.follow(new Vector2d(0, 0)) && grassPosition.precedes(new Vector2d(map.grassUpperBound, map.grassUpperBound));
            } else {
                assert element instanceof Animal;
            }
        }
    }
    @Test
    @DisplayName("Tests large coordinates")
    void testBigField() {
        MovesGenerator generator = new MovesGenerator(2022);
        final int n = 2000;
        Vector2d startingPosition = new Vector2d(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);

        GrassField map = new GrassField(0);
        Vector2d[] positions = {startingPosition, startingPosition.opposite()};
        MoveDirection[] directions = new MoveDirection[n];
        Arrays.fill(directions, MoveDirection.FORWARD);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assert map.elementsList.size() == positions.length;
        assert map.elementsList.get(0) instanceof Animal;
        Vector2d animalPosition1 = map.elementsList.get(0).getPosition();
        Vector2d animalPosition2 = map.elementsList.get(1).getPosition();
        assert animalPosition1.equals(new Vector2d(startingPosition.x, startingPosition.y + n/2));
        assert animalPosition2.equals(new Vector2d(-startingPosition.x, -startingPosition.y + n/2));
    }
}
