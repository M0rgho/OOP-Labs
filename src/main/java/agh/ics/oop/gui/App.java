package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private GrassField map;

    @Override
    public void start(Stage primaryStage) {

        String[] args = getParameters().getRaw().toArray(new String[0]);

        int grassCount = 10;
        try {
            MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "f"});
            map = new GrassField(grassCount);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3)};

            IEngine engine = new SimulationEngine(directions, map, positions);

            engine.run();

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        int width = map.mapBoundary.getUpperRight().x - map.mapBoundary.getLowerLeft().x;
        int height = map.mapBoundary.getUpperRight().y - map.mapBoundary.getLowerLeft().y;
        System.out.println(width + "   " + height);

        GridPane grid = new GridPane();

//        grid.getRowConstraints().add(new RowConstraints(20));
        for(int i = 0; i < width; ++i) {
            grid.add(new Text("X"), i, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(20));

        }
        grid.add(new Text("X"), 0, 1, 1, 1);

        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
