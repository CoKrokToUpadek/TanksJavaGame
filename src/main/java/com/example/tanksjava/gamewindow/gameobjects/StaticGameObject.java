package com.example.tanksjava.gamewindow.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StaticGameObject {
   private final ImageView objectGraphics;
   private final String objectURLString;
   private final int objectSizeX;

   private final int objectSizeY;
   private final int objectStartingPositionX;
   private final int objectStartingPositionY;
   private final boolean isDestructible;

   private final int initialRotation;




    public StaticGameObject(String objectURLString, int objectSizeX, int objectSizeY, int objectStartingPositionX, int objectStartingPositionY, boolean isDestructible, int initialRotation) {
        this.objectURLString = objectURLString;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.objectStartingPositionX = objectStartingPositionX;
        this.objectStartingPositionY = objectStartingPositionY;
        this.isDestructible = isDestructible;
        this.objectGraphics=new ImageView(new Image(objectURLString));
        this.initialRotation=initialRotation;

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
        objectGraphics.setLayoutX(objectStartingPositionX);
        objectGraphics.setLayoutY(objectStartingPositionY);
        objectGraphics.setRotate(initialRotation);
    }

    public int getInitialRotation() {
        return initialRotation;
    }
}
