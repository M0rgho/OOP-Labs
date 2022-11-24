package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class GridGui extends GridPane{

    private final GrassField map;
    private final double cellSize;
    private final GuiElementBox cellFactory;

    private final List<Pair<Vector2d, Node>> guiCells = new ArrayList<>();

    GridGui(GrassField map ,double cellSize) {
        this.map = map;
        this.cellSize = cellSize;
        this.cellFactory = new GuiElementBox(cellSize);
        createAnimalGrid();
        setGridLinesVisible(true);
    }


    private void createAnimalGrid() {

        int minX = map.mapBoundary.getLowerLeft().x;
        int maxX = map.mapBoundary.getUpperRight().x;
        int minY = map.mapBoundary.getLowerLeft().y;
        int maxY = map.mapBoundary.getUpperRight().y;

        int width = maxX - minX + 1;
        int height = maxY - minY + 1;

        add(getCenteredText("y/x"), 0, 0, 1, 1);
        getColumnConstraints().add(new ColumnConstraints(cellSize));
        getRowConstraints().add(new RowConstraints(cellSize));


        // create x-axis column
        for(int i = 0; i < width; ++i) {
            getColumnConstraints().add(new ColumnConstraints(cellSize));
            add(getCenteredText(Integer.toString(i + minX)), i + 1, 0, 1, 1);
        }
        // create y-axis row
        for(int i = 0; i < height; ++i) {
            getRowConstraints().add(new RowConstraints(cellSize));
            add(getCenteredText(Integer.toString(maxY - i)), 0, i + 1, 1, 1);
        }

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                add(getMapObject(new Vector2d(minX + j, maxY - i)), j + 1, i + 1, 1, 1);
            }
        }

    }

    private Node getMapObject(Vector2d position) {
        return cellFactory.createNode((IMapElement) map.objectAt(position));
    }

    private Text getCenteredText(String textInside) {
        Text text = new Text();
        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setValignment(text, VPos.CENTER);
        text.setText(textInside);
        text.setFont(new Font(20));
        return text;
    }

    public void refresh() {
        getChildren().clear();
        getColumnConstraints().clear();
        getRowConstraints().clear();
        createAnimalGrid();
        setGridLinesVisible(true);
    }
}
