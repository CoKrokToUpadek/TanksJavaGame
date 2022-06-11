package com.example.tanksjava.gamewindow.gameobjects.game_objects.shells;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.TankGameObject;
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

    public ShellGameObject(ShellGameObject shellGameObject, TankGameObject playerObject) {
        super(shellGameObject.getObjectFlag().getFlagValue(), shellGameObject.getObjectURLString(), shellGameObject.getObjectSizeX(), shellGameObject.getObjectSizeY(), shellGameObject.isDestructible());
        this.speed=shellGameObject.speed;
        objectMovementInitialization(playerObject);
    }

    public void objectMovementInitialization(TankGameObject playerObject) {
        int tempStartingPositionX=0;
        int tempStartingPositionY=0;
        int tempRotation;

        int tempPlayerStartingPositionX=playerObject.getTankDirectionController().getCurrentBarrelPositionX();
        int tempPlayerStartingPositionY=playerObject.getTankDirectionController().getCurrentBarrelPositionY();
        tempRotation=playerObject.getTankDirectionController().getObjectRotation();


        switch (playerObject.getTankDirectionController().getObjectRotation()){
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
             this.getObjectGraphics().setViewOrder(2);
        }
        deletionFlag=shellDirectionController.updateShellPosition();
        this.getObjectGraphics().setRotate(shellDirectionController.getObjectRotation());
        this.getObjectGraphics().setLayoutY(shellDirectionController.getCurrentPositionY());
        this.getObjectGraphics().setLayoutX(shellDirectionController.getCurrentPositionX());
        if (deletionFlag){
            pane.getChildren().remove(this.getObjectGraphics());
            return true;
        }

        return false;
    }



}
