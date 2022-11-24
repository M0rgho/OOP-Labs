package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver{
    private GrassField map;

    private double appWidth = 600;
    private double appHeight = 600;
    private final double cellSize = 40;

    private final int moveDelay = 300; // milliseconds

    private GridGui gridGui;
    private SimulationEngine engine;


    @Override
    public void start(Stage primaryStage) {

        String[] args = getParameters().getRaw().toArray(new String[0]);
        try {
            runSimulation(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        Button startButton = new Button("START");
        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            startSimulation();
        });
        HBox navPanel = new HBox(startButton, new TextField());
        gridGui = new GridGui(map, cellSize);
        VBox mainElement = new VBox(navPanel, gridGui);

        Scene scene = new Scene(mainElement, appWidth, appHeight);
//        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animals Simulation");
        primaryStage.show();
    }

    private void startSimulation() {
        new Thread(engine::run).start();
    }

    private void runSimulation(String[] args) {
        int grassCount = 10;
        MoveDirection[] directions = OptionsParser.parse(args);
        map = new GrassField(grassCount);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(2, 3)};

        engine = new ThreadedSimulationEngine(directions, map, positions, moveDelay);
        engine.addObserver(this);
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> gridGui.refresh());
    }
}
