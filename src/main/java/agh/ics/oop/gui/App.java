package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private GrassField map;

    private final int appWidth = 600;
    private final int appHeight = 600;

    @Override
    public void start(Stage primaryStage) {

        String[] args = getParameters().getRaw().toArray(new String[0]);
        try {
            runSimulation(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        GridPane animalGrid = createAnimalGrid();

        Scene scene = new Scene(animalGrid, appWidth, appHeight);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animals Simulation");
        primaryStage.show();
    }

    private void runSimulation(String[] args) {
        int grassCount = 10;
        MoveDirection[] directions = OptionsParser.parse(args);
        map = new GrassField(grassCount);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3)};

        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();
    }

    private GridPane createAnimalGrid() {
        int minX = map.mapBoundary.getLowerLeft().x;
        int maxX = map.mapBoundary.getUpperRight().x;
        int minY = map.mapBoundary.getLowerLeft().y;
        int maxY = map.mapBoundary.getUpperRight().y;

        int width = maxX - minX + 1;
        int height = maxY - minY + 1;

        double elWidth = (double) appWidth / (width + 1);
        double elHeight = (double) appHeight /(height + 1);

        GridPane grid = new GridPane();
        grid.add(getCenteredText("y/x"), 0, 0, 1, 1);
        grid.getColumnConstraints().add(new ColumnConstraints(elWidth));
        grid.getRowConstraints().add(new RowConstraints(elHeight));

        // create x-axis column
        for(int i = 0; i < width; ++i) {
            grid.getColumnConstraints().add(new ColumnConstraints(elWidth));
            grid.add(getCenteredText(Integer.toString(i + minX)), i + 1, 0, 1, 1);
        }
        // create y-axis row
        for(int i = 0; i < height; ++i) {
            grid.getRowConstraints().add(new RowConstraints(elHeight));
            grid.add(getCenteredText(Integer.toString(maxY - i)), 0, i + 1, 1, 1);
        }

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                grid.add(getMapObject(new Vector2d(minX + j, maxY - i)), j + 1, i + 1, 1, 1);
            }
        }
        grid.setGridLinesVisible(true);

        return grid;
    }

    private Text getMapObject(Vector2d position) {
        Object element = map.objectAt(position);
        Text text = getCenteredText(element != null ? element.toString() : " ");
        if (element instanceof Grass) {
            text.setFill(Color.GREEN);
        }
        return text;
    }

    private Text getCenteredText(String textInside) {
        Text text = new Text();
        GridPane.setHalignment(text, HPos.CENTER);
        text.setText(textInside);
        text.setFont(new Font(20));
        return text;
    }

}
