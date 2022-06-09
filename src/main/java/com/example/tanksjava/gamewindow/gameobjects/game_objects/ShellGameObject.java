package com.example.tanksjava.gamewindow.gameobjects.game_objects;

import javafx.scene.layout.Pane;

public class ShellGameObject extends MovableGameObject{

    private final MovableGameObject owner;



    public ShellGameObject(MovableGameObject owner, int objectFlag, String objectURLString, int objectSizeX, int objectSizeY, int objectSpeed, boolean isDestructible) {
        super(0,objectFlag,objectURLString,objectSizeX,objectSizeY,0,0,isDestructible,0,null,objectSpeed);
        this.owner=owner;
        this.setHitBoxController(owner.getHitBoxController());

    }

    public void objectMovementInitialization(Pane pane) {

        setInitialPositionAndRotation(owner.getPlayerDirectionController().getObjectRotation(), owner.getPlayerDirectionController().getCurrentBarrelPositionX(),owner.getPlayerDirectionController().getCurrentBarrelPositionY());
        //pane.getChildren().add(this.getObjectGraphics());
        insertObjectOnToPane(pane);
        this.getObjectGraphics().setViewOrder(3);


    }

}
