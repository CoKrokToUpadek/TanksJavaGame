package com.example.tanksjava.gamewindow.gameobjects;

import com.example.tanksjava.gamewindow.HitBoxController;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class StaticGameObject {
    private ImageView objectGraphics;

    private final String objectURLString;



    private final int objectSizeX;

    private final int objectSizeY;
    private int objectStartingPositionX;
    private int objectStartingPositionY;
    private final boolean isDestructible;

    private int initialRotation;

    private final int objectFlag;

    private HitBoxController hitBoxController;


    public StaticGameObject(int objectFlag, String objectURLString, int objectSizeX, int objectSizeY, int objectStartingPositionX,
                            int objectStartingPositionY, boolean isDestructible, int initialRotation, HitBoxController hitBoxController) {
        this.objectURLString = objectURLString;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.objectStartingPositionX = objectStartingPositionX;
        this.objectStartingPositionY = objectStartingPositionY;
        this.isDestructible = isDestructible;
        this.objectGraphics = new ImageView(new Image(objectURLString));
        this.initialRotation = initialRotation;
        this.hitBoxController = hitBoxController;
        this.objectFlag = objectFlag;

    }

    public StaticGameObject(int objectFlag, String objectURLString, int objectSizeX, int objectSizeY, boolean isDestructible) {
        this.objectURLString = objectURLString;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.isDestructible = isDestructible;
        this.objectGraphics = new ImageView(new Image(objectURLString));
        this.objectFlag = objectFlag;
    }


    public StaticGameObject(StaticGameObject object) {
        this.objectURLString = object.objectURLString;
        this.objectSizeX = object.objectSizeX;
        this.objectSizeY = object.objectSizeY;
        this.isDestructible = object.isDestructible;
        this.objectGraphics = new ImageView(new Image(objectURLString));
        this.objectFlag = object.objectFlag;
    }

    public int getObjectSizeX() {
        return objectSizeX;
    }

    public int getObjectSizeY() {
        return objectSizeY;
    }

    public ImageView getObjectGraphics() {
        return objectGraphics;
    }

    public String getObjectURLString() {
        return objectURLString;
    }

    public int getInitialRotation() {
        return initialRotation;
    }

    public int getObjectFlag() {
        return objectFlag;
    }

    public int getObjectStartingPositionX() {
        return objectStartingPositionX;
    }

    public int getObjectStartingPositionY() {
        return objectStartingPositionY;
    }

    public HitBoxController getHitBoxController() {
        return hitBoxController;
    }


   public void setImageGraphic(String newImage) {
        this.objectGraphics.setImage(new Image(newImage));
   }

    public void insertObjectOnToPane(Pane pane) {
        StaticToolsAndHandlers.insertObjectOnToPane(pane, this, objectStartingPositionX, objectStartingPositionY, initialRotation, hitBoxController);
    }


}
