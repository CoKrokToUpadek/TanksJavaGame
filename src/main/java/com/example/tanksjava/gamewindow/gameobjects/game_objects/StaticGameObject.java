package com.example.tanksjava.gamewindow.gameobjects.game_objects;

import com.example.tanksjava.gamewindow.hibox_controllers.Flag;
import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class StaticGameObject {
    private final ImageView objectGraphics;

    private final String objectURLString;

    private final int objectSizeX;

    private final int objectSizeY;
    private int objectStartingPositionX;
    private int objectStartingPositionY;
    private final boolean isDestructible;

    private int initialRotation;

    private final Flag objectFlag;

    private HitBoxController hitBoxController;

    private final int gameObjectID;


    //for active objects
    public StaticGameObject(int objectFlag, String objectURLString, int objectSizeX, int objectSizeY, int objectStartingPositionX,
                            int objectStartingPositionY, boolean isDestructible, int initialRotation,HitBoxController hitBoxController) {



        this.objectURLString = objectURLString;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.objectStartingPositionX = objectStartingPositionX;
        this.objectStartingPositionY = objectStartingPositionY;
        this.isDestructible = isDestructible;
        this.objectGraphics = new ImageView(new Image(objectURLString));
        this.initialRotation = initialRotation;
        this.hitBoxController = hitBoxController;
        this.gameObjectID=StaticToolsAndHandlers.getObjectNumber();

        this.objectFlag = new Flag(objectFlag);
        this.objectFlag.setObject(this);
    }
    //temporary objects that doesn't have hitbox
    public StaticGameObject(int objectFlag, String objectURLString, int objectSizeX, int objectSizeY, boolean isDestructible) {
        this.objectURLString = objectURLString;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.isDestructible = isDestructible;
        this.objectGraphics = new ImageView(new Image(objectURLString));

        gameObjectID=0;

        this.objectFlag = new Flag(objectFlag);
        this.objectFlag.setObject(this);
    }

    //for static game objects added in bulk
    public StaticGameObject(StaticGameObject object) {
        this.objectURLString = object.objectURLString;
        this.objectSizeX = object.objectSizeX;
        this.objectSizeY = object.objectSizeY;
        this.isDestructible = object.isDestructible;
        this.objectGraphics = new ImageView(new Image(objectURLString));


        this.objectFlag = new Flag(object.getObjectFlag().getFlagValue());
        this.objectFlag.setObject(this);

        this.gameObjectID=StaticToolsAndHandlers.getObjectNumber();
        System.out.println(gameObjectID);
    }

    public void setInitialPositionAndRotation(int initialRotation, int objectStartingPositionX, int objectStartingPositionY){
        this.initialRotation=initialRotation;
        this.objectStartingPositionX=objectStartingPositionX;
        this.objectStartingPositionY=objectStartingPositionY;
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

    public Flag getObjectFlag() {
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


    public void setHitBoxController(HitBoxController hitBoxController) {
        this.hitBoxController = hitBoxController;
    }

    public void setImageGraphic(String newImage) {
        this.objectGraphics.setImage(new Image(newImage));
   }

    public void setObjectStartingPositionX(int objectStartingPositionX) {
        this.objectStartingPositionX = objectStartingPositionX;
    }

    public void setObjectStartingPositionY(int objectStartingPositionY) {
        this.objectStartingPositionY = objectStartingPositionY;
    }

    public void insertObjectOnToPane(Pane pane) {
        StaticToolsAndHandlers.insertObjectOnToPane(pane, this, objectStartingPositionX, objectStartingPositionY, initialRotation, hitBoxController);
    }

    public int getGameObjectID() {
        return gameObjectID;
    }

    public boolean isDestructible() {
        return isDestructible;
    }


}
