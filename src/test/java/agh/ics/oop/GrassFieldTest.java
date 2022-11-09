package agh.ics.oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GrassFieldTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20, 40, 100})
    @DisplayName("Test grass generation")
    void grassGenerationTest(final int n) {
        GrassField grassField = new GrassField(n);
        assert grassField.grassCount == n;
        assert grassField.elementsList.size() == n;
        for(IMapElement element : grassField.elementsList) {
            assert element instanceof Grass;
            assert element.getPosition().precedes(new Vector2d(grassField.grassUpperBound, grassField.grassUpperBound));
        }
    }
}
