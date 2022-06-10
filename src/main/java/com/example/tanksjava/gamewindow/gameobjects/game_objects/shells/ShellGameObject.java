package com.example.tanksjava.gamewindow.gameobjects.game_objects.shells;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.MovableGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.ObjectDirectionController;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.StaticGameObject;
import javafx.scene.layout.Pane;

public class ShellGameObject extends StaticGameObject {

    private final int speed;

    private ObjectDirectionController shellDirectionController;
    public ShellGameObject(int objectFlag, String objectURLString, int objectSizeX, int objectSizeY, boolean isDestructible,int speed) {
     super(objectFlag,objectURLString,objectSizeX,objectSizeY,isDestructible);
     this.speed=speed;
    }

    public ShellGameObject(ShellGameObject shellGameObject, MovableGameObject playerObject) {
        super(shellGameObject.getObjectFlag().getFlagValue(), shellGameObject.getObjectURLString(), shellGameObject.getObjectSizeX(), shellGameObject.getObjectSizeY(), shellGameObject.isDestructible());
        this.speed=shellGameObject.speed;
        objectMovementInitialization(playerObject);
    }

    public void objectMovementInitialization(MovableGameObject playerObject) {
        int tempStartingPositionX=0;
        int tempStartingPositionY=0;
        int tempRotation;

        int tempPlayerStartingPositionX=playerObject.getPlayerDirectionController().getCurrentBarrelPositionX();
        int tempPlayerStartingPositionY=playerObject.getPlayerDirectionController().getCurrentBarrelPositionY();
        tempRotation=playerObject.getPlayerDirectionController().getObjectRotation();


        switch (playerObject.getPlayerDirectionController().getObjectRotation()){
            case 0:
                tempStartingPositionX=tempPlayerStartingPositionX-(this.getObjectSizeX()/2);
                tempStartingPositionY=tempPlayerStartingPositionY-this.getObjectSizeY();
                break;
            case 180:
                tempStartingPositionX=tempPlayerStartingPositionX-(this.getObjectSizeX()/2);
                tempStartingPositionY=tempPlayerStartingPositionY;

                break;
            case 90:
                tempStartingPositionX=tempPlayerStartingPositionX+this.getObjectSizeY();
                tempStartingPositionY=tempPlayerStartingPositionY-(this.getObjectSizeX());
                break;
            case 270:
                tempStartingPositionX=tempPlayerStartingPositionX-this.getObjectSizeY();
                tempStartingPositionY=tempPlayerStartingPositionY-(this.getObjectSizeX());
                break;
        }

        shellDirectionController=new ObjectDirectionController(tempStartingPositionX,tempStartingPositionY,this.getObjectSizeX(),
                this.getObjectSizeY(),this.speed,tempRotation,playerObject.getHitBoxController());
    }

    public boolean objectPositionAndOrientationUpdater(Pane pane){
        boolean deletionFlag=false;
        if (!pane.getChildren().contains(this.getObjectGraphics())){
             pane.getChildren().add(this.getObjectGraphics());
        }
        deletionFlag=shellDirectionController.updateShellPosition();
        this.getObjectGraphics().setRotate(shellDirectionController.getObjectRotation());
        this.getObjectGraphics().setLayoutY(shellDirectionController.getCurrentPositionY());
        this.getObjectGraphics().setLayoutX(shellDirectionController.getCurrentPositionX());
        if (deletionFlag){
            this.getObjectGraphics().setVisible(false);
            return true;
        }

        return false;
    }



}
