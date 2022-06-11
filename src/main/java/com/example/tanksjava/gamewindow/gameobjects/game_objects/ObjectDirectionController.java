package com.example.tanksjava.gamewindow.gameobjects.game_objects;

import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;
import com.example.tanksjava.mainmenuwindow.MainMenuController;


public class ObjectDirectionController {
    private int currentPositionX;
    private int currentPositionY;

    private int currentBarrelPositionX;
    private int currentBarrelPositionY;

    private int objectRotation;

    private final int objectSizeX;
    private final int objectSizeY;

    private int objectSpeed;

    private HitBoxController hitBoxController;

    public ObjectDirectionController(int startingPositionX, int staringPositionY, int objectSizeX, int objectSizeY, int objectSpeed, int initialRotation , HitBoxController hitBoxController) {
        this.currentPositionY = staringPositionY;
        this.currentPositionX = startingPositionX;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.objectSpeed = objectSpeed;
        this.hitBoxController = hitBoxController;
        this.objectRotation =initialRotation;

    }


    public void updateBarrelPositionForVehicles() {
        switch (objectRotation) {
            case 180:
                currentBarrelPositionX = (currentPositionX + (objectSizeX/2)) ;
                currentBarrelPositionY = currentPositionY;
                break;
            case 0:
                currentBarrelPositionX = (currentPositionX + (objectSizeX/2));
                currentBarrelPositionY = currentPositionY + objectSizeY;
                break;
            case 90:
                currentBarrelPositionX = currentPositionX;
                currentBarrelPositionY = (currentPositionY + (objectSizeY/2));
                break;
            case 270:
                currentBarrelPositionX = (currentPositionX + objectSizeX);
                currentBarrelPositionY = (currentPositionY + (objectSizeY/2));
                break;
        }


    }
    //TODO need changes
    public boolean updateObjectPosition() {
        boolean collisionDetectedForAI=false;
        switch (objectRotation) {
            case 180:
                if (!(currentPositionY <= 0)) {
                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 180, objectSpeed) >= objectSpeed) {
                        currentPositionY -= objectSpeed;
                    } else {
                        currentPositionY -= hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 180, objectSpeed);
                        collisionDetectedForAI=true;
                    }
                }
                break;
            case 0:
                if (!(currentPositionY >= MainMenuController.getGameWindowHeight() - objectSizeY)) {

                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 0, objectSpeed) >= objectSpeed) {
                        currentPositionY += objectSpeed;
                    } else {
                        currentPositionY += hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 0, objectSpeed);
                        collisionDetectedForAI=true;
                    }

                }
                break;
            case 90:
                if (!(currentPositionX <= 0)) {

                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 90, objectSpeed) >= objectSpeed) {
                        currentPositionX -= objectSpeed;
                    } else {
                        currentPositionX -= hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 90, objectSpeed);
                        collisionDetectedForAI=true;
                    }


                }
                break;
            case 270:
                if (!(currentPositionX >= MainMenuController.getGameWindowWidth() - objectSizeX)) {

                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1,270, objectSpeed) >= objectSpeed) {
                        currentPositionX += objectSpeed;
                    } else {
                        currentPositionX += hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 270, objectSpeed);
                        collisionDetectedForAI=true;
                    }

                }
                break;
            default:
                break;

        }
        return collisionDetectedForAI;
    }


    public boolean updateShellPosition() {

        switch (objectRotation) {
            case 180:
                if (!(currentPositionY <= 0)) {
                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 180, objectSpeed) >= objectSpeed) {
                        currentPositionY -= objectSpeed;

                    }else{
                        return true;
                    }
                }
                break;
            case 0:
                if (!(currentPositionY >= MainMenuController.getGameWindowHeight() - objectSizeY)) {

                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 0, objectSpeed) >= objectSpeed) {
                        currentPositionY += objectSpeed;

                    }else{
                        return true;
                    }

                }
                break;
            case 90:
                if (!(currentPositionX <= 0)) {

                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1, 90, objectSpeed) >= objectSpeed) {
                        currentPositionX -= objectSpeed;
                    }else {
                        return true;
                    }
                }
                break;
            case 270:
                if (!(currentPositionX >= MainMenuController.getGameWindowWidth() - objectSizeX)) {

                    if (hitBoxController.detectCollisionsForMovementOfNonDestructibleObject(this, 1,270, objectSpeed) >= objectSpeed) {
                        currentPositionX += objectSpeed;
                    }else {
                        return true;
                    }

                }
                break;
            default:
                break;
        }
        return false;
    }



//    public void playerRotation() {
//
//        switch (objectRotation) {
//            case 'w':
//                objectRotation = 180;
//                break;
//            case 's':
//                objectRotation = 0;
//                break;
//            case 'a':
//                objectRotation = 90;
//                break;
//            case 'd':
//                objectRotation = 270;
//                break;
//            default:
//                break;
//        }
//    }


    public int getObjectSizeX() {
        return objectSizeX;
    }

    public int getObjectSizeY() {
        return objectSizeY;
    }

    public int getCurrentPositionX() {
        return currentPositionX;
    }

    public int getCurrentPositionY() {
        return currentPositionY;
    }

    public int getObjectRotation() {
        return objectRotation;
    }

    public int getCurrentBarrelPositionX() {return currentBarrelPositionX;}

    public int getCurrentBarrelPositionY() {return currentBarrelPositionY;}

    public void setObjectRotation(int objectRotation) {
        this.objectRotation = objectRotation;
    }
}
