package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.Grass;
import agh.ics.oop.IMapElement;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox extends Node {
    private final double cellSize;

    public GuiElementBox(double cellSize) {
        super();
        this.cellSize = cellSize;
    }

    public Node createNode(IMapElement element) {
        if (element == null)
            return new Text();
        Image image;
        try {
            image = new Image(new FileInputStream(element.getGraphicRepresentation()));
        } catch (FileNotFoundException e) {
            return new Text("ERROR");
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(cellSize/2);
        imageView.setFitWidth(cellSize/2);
        VBox vBox = new VBox(imageView, getCaption(element));
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle(element instanceof Grass ? "-fx-background-color: #0efd0e" : "-fx-background-color: #88dbe0");
        return vBox;
    }

    private Text getCaption(IMapElement element) {
        Text text = new Text();
        text.setFont(new Font(cellSize/3));
        if (element instanceof Grass) {
            text.setText("Grass");
        } else if (element instanceof Animal) {
            Animal animal = (Animal) element;
            text.setText(animal.getDirection().toString() + " " + animal.getPosition());
        }
        GridPane.setHalignment(text, HPos.CENTER);
        return text;
    }

}
