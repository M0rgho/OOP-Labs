package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver{
    private GrassField map;

    private double appWidth = 600;
    private double appHeight = 600;
    private final double cellSize = 40;

    private final int moveDelay = 300; // milliseconds

    private GridGui gridGui;
    private SimulationEngine engine;
    private final Text infoText = new Text();
    private final Button startButton = new Button("START");

    private final TextField movesInput = new TextField();
    @Override
    public void start(Stage primaryStage) {

        prepareEngine();


        movesInput.setPromptText("Enter moves");
        infoText.setFont(new Font(14));
        infoText.setTextAlignment(TextAlignment.CENTER);
        startButton.setOnAction(event -> {
            try {
                MoveDirection[] direction = OptionsParser.parse(movesInput.getCharacters().toString().split("\\s+"));
                engine.setDirections(direction);
                startButton.setDisable(true);
                startSimulation();
            } catch (IllegalArgumentException e) {
                infoText.setStroke(Color.RED);
                infoText.setText("Invalid move: " + e.getMessage());
                System.err.println(e.getMessage());
            }
        });


        HBox navPanel = new HBox(5, startButton, movesInput, infoText);
        gridGui = new GridGui(map, cellSize);
        VBox mainElement = new VBox(5, navPanel, gridGui);

        Scene scene = new Scene(mainElement, appWidth, appHeight);
//        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animals Simulation");
        primaryStage.show();
    }

    private void startSimulation() {
        infoText.setText("");
        new Thread(() -> {
            Thread t = new Thread(engine::run);
            try {
                t.start();
                t.join();
                infoText.setText("Simulation has run successfully");
                infoText.setStroke(Color.GREEN);
                startButton.setDisable(false);
            } catch (InterruptedException e) {
                System.err.println("Thread execution was abrupted");
                infoText.setStroke(Color.RED);
                infoText.setText("Simulation error: " + e.getMessage());
            }

        }).start();
    }

    private void prepareEngine() {
        int grassCount = 10;
        map = new GrassField(grassCount);
        Vector2d[] positions = {new Vector2d(4, 4), new Vector2d(6, 4)};
        engine = new ThreadedSimulationEngine(new MoveDirection[]{}, map, positions, moveDelay);
        engine.addObserver(this);
    }



    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> gridGui.refresh());
    }
}
