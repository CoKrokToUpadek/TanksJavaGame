package com.example.tanksjava.gamewindow.gameobjects.game_objects.shells;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.TankGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.ObjectDirectionController;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.StaticGameObject;
import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.scene.layout.Pane;

public class ShellGameObject extends StaticGameObject {

    private final int speed;

    private ObjectDirectionController shellDirectionController;
    public ShellGameObject(int objectFlag, String objectURLString, int objectSizeX, int objectSizeY, boolean isDestructible, int speed, HitBoxController hitBoxController) {
     super(objectFlag,objectURLString,objectSizeX,objectSizeY,isDestructible);
     setHitBoxController(hitBoxController);
     this.speed=speed;
    }

    public ShellGameObject(ShellGameObject shellGameObject, TankGameObject playerObject) {
        super(shellGameObject.getObjectFlag().getFlagValue(), shellGameObject.getObjectURLString(), shellGameObject.getObjectSizeX(), shellGameObject.getObjectSizeY(), shellGameObject.isDestructible());
        this.speed=shellGameObject.speed;
        setHitBoxController(playerObject.getHitBoxController());
        objectMovementInitialization(playerObject);
    }

    public void objectMovementInitialization(TankGameObject playerObject) {
        int tempStartingPositionX=0;
        int tempStartingPositionY=0;
        int tempRotation;

        int tempPlayerStartingPositionX=playerObject.getTankDirectionController().getCurrentBarrelPositionX();
        int tempPlayerStartingPositionY=playerObject.getTankDirectionController().getCurrentBarrelPositionY();
        tempRotation=playerObject.getTankDirectionController().getObjectRotation();
        //rotation ok

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
                tempStartingPositionX=tempPlayerStartingPositionX;
                tempStartingPositionY=tempPlayerStartingPositionY-(this.getObjectSizeX());
                break;
            case 270:
                tempStartingPositionX=tempPlayerStartingPositionX;
                tempStartingPositionY=tempPlayerStartingPositionY-(this.getObjectSizeX());
                break;
        }

        shellDirectionController=new ObjectDirectionController(tempStartingPositionX,tempStartingPositionY,this.getObjectSizeX(),
                this.getObjectSizeY(),this.speed,tempRotation,playerObject.getHitBoxController());
    }

    public boolean objectPositionAndOrientationUpdater(Pane pane) {

        if (!pane.getChildren().contains(this.getObjectGraphics())){
             pane.getChildren().add(this.getObjectGraphics());
             this.getObjectGraphics().setViewOrder(2);
        }
        StaticToolsAndHandlers.updateShellHitBox(this,getHitBoxController());
        shellDirectionController.updateShellPositionV2();
        this.getObjectGraphics().setRotate(shellDirectionController.getObjectRotation());
        this.getObjectGraphics().setLayoutY(shellDirectionController.getCurrentPositionY());
        this.getObjectGraphics().setLayoutX(shellDirectionController.getCurrentPositionX());
        StaticToolsAndHandlers.updateShellHitBox(this,getHitBoxController());
        if (this.shellDirectionController.getOwnerGotHit()){
            StaticToolsAndHandlers.clearObjectHitBox(getShellDirectionController(),getHitBoxController());
            pane.getChildren().remove(this.getObjectGraphics());
            return true;
        }

        return false;
    }

    public ObjectDirectionController getShellDirectionController() {
        return shellDirectionController;
    }
}
