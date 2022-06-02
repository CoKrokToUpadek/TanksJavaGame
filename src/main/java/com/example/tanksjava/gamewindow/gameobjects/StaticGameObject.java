package com.example.tanksjava.gamewindow.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StaticGameObject {
   private final ImageView objectGraphics;
   private final String objectURLString;
   private final int pixelSizeX;
   private final int pixelSizeY;
   private final int objectStartingPositionX;
   private final int objectStartingPositionY;
   private final boolean isDestructible;




    public StaticGameObject(String objectURLString, int pixelSizeX, int pixelSizeY, int objectStartingPositionX, int objectStartingPositionY, boolean isDestructible) {
        this.objectURLString = objectURLString;
        this.pixelSizeX = pixelSizeX;
        this.pixelSizeY = pixelSizeY;
        this.objectStartingPositionX = objectStartingPositionX;
        this.objectStartingPositionY = objectStartingPositionY;
        this.isDestructible = isDestructible;
        this.objectGraphics=new ImageView(new Image(objectURLString));

    }

    public ImageView getObjectGraphics() {
        return objectGraphics;
    }

    public int getObjectStartingPositionX() {
        return objectStartingPositionX;
    }

    public int getObjectStartingPositionY() {
        return objectStartingPositionY;
    }

    public void insertObjectOnToPane(Pane pane){
        pane.getChildren().add(objectGraphics);
        objectGraphics.setLayoutX(getObjectStartingPositionY());
        objectGraphics.setLayoutY(objectStartingPositionY);
    }

}
