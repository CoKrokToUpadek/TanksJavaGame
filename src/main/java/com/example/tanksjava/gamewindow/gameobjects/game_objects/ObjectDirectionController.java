package com.example.tanksjava.gamewindow.gameobjects.game_objects;

import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;
import com.example.tanksjava.mainmenuwindow.MainMenuController;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;


public class ObjectDirectionController {
    private int currentPositionX;
    private int currentPositionY;

    private int currentBarrelPositionX;
    private int currentBarrelPositionY;

    private int objectRotation;

    private final int objectSizeX;
    private final int objectSizeY;

    private final int objectSpeed;

    private boolean ownerGotHit;

    private final HitBoxController hitBoxController;

    public ObjectDirectionController(int startingPositionX, int staringPositionY, int objectSizeX, int objectSizeY, int objectSpeed, int initialRotation, HitBoxController hitBoxController) {
        this.currentPositionY = staringPositionY;
        this.currentPositionX = startingPositionX;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.objectSpeed = objectSpeed;
        this.hitBoxController = hitBoxController;
        this.objectRotation = initialRotation;
        this.ownerGotHit = false;

    }


    public void updateBarrelPositionForVehicles() {
        switch (objectRotation) {
            case 180:
                currentBarrelPositionX = (currentPositionX + (objectSizeX / 2));
                currentBarrelPositionY = currentPositionY;
                break;
            case 0:
                currentBarrelPositionX = (currentPositionX + (objectSizeX / 2));
                currentBarrelPositionY = currentPositionY + objectSizeY;
                break;
            case 90:
                currentBarrelPositionX = currentPositionX;
                currentBarrelPositionY = (currentPositionY + (objectSizeY / 2));
                break;
            case 270:
                currentBarrelPositionX = (currentPositionX + objectSizeX);
                currentBarrelPositionY = (currentPositionY + (objectSizeY / 2));
                break;
        }


    }


    public boolean updateTankPositionV2() {
        boolean collisionDetectedForAI = false;
        switch (objectRotation) {
            case 180:
                if (!(currentPositionY <= 0)) {
                    if (hitBoxController.detectCollisionsForMovement(this, 180, objectSpeed) >= objectSpeed) {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionY -= objectSpeed;
                    } else {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionY -= hitBoxController.detectCollisionsForMovement(this, 180, objectSpeed);
                        collisionDetectedForAI = true;
                    }
                }
                break;
            case 0:
                if (!(currentPositionY >= MainMenuController.getGameWindowHeight() - objectSizeY)) {

                    if (hitBoxController.detectCollisionsForMovement(this, 0, objectSpeed) >= objectSpeed) {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionY += objectSpeed;
                    } else {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionY += hitBoxController.detectCollisionsForMovement(this, 0, objectSpeed);
                        collisionDetectedForAI = true;
                    }

                }
                break;
            case 90:
                if (!(currentPositionX <= 0)) {

                    if (hitBoxController.detectCollisionsForMovement(this, 90, objectSpeed) >= objectSpeed) {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionX -= objectSpeed;
                    } else {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionX -= hitBoxController.detectCollisionsForMovement(this, 90, objectSpeed);
                        collisionDetectedForAI = true;
                    }


                }
                break;
            case 270:
                if (!(currentPositionX >= MainMenuController.getGameWindowWidth() - objectSizeX)) {

                    if (hitBoxController.detectCollisionsForMovement(this, 270, objectSpeed) >= objectSpeed) {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionX += objectSpeed;
                    } else {
                        StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                        currentPositionX += hitBoxController.detectCollisionsForMovement(this, 270, objectSpeed);
                        collisionDetectedForAI = true;
                    }

                }
                break;
            default:
                break;

        }
        return collisionDetectedForAI;
    }


    public void updateShellPositionV2() {

        switch (objectRotation) {
            case 180:
                if (!(currentPositionY <= 0)) {
                    hitBoxController.detectCollisionsForMovement(this, 180, objectSpeed);
                    StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                    currentPositionY -= objectSpeed;
                }
                break;
            case 0:
                if (!(currentPositionY >= MainMenuController.getGameWindowHeight() - objectSizeY)) {
                    hitBoxController.detectCollisionsForMovement(this, 0, objectSpeed);
                    StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                    currentPositionY += objectSpeed;

                }
                break;
            case 90:
                if (!(currentPositionX <= 0)) {
                    hitBoxController.detectCollisionsForMovement(this, 90, objectSpeed);
                    StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                    currentPositionX -= objectSpeed;
                }
                break;
            case 270:
                if (!(currentPositionX >= MainMenuController.getGameWindowWidth() - objectSizeX)) {
                    hitBoxController.detectCollisionsForMovement(this, 270, objectSpeed);
                    StaticToolsAndHandlers.clearObjectHitBox(this, hitBoxController);
                    currentPositionX += objectSpeed;
                }
                break;
            default:
                break;
        }

    }


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

    public int getCurrentBarrelPositionX() {
        return currentBarrelPositionX;
    }

    public int getCurrentBarrelPositionY() {
        return currentBarrelPositionY;
    }

    public void setObjectRotation(int objectRotation) {
        this.objectRotation = objectRotation;
    }

    public boolean getOwnerGotHit() {
        return ownerGotHit;
    }

    public void setOwnerGotHit(boolean ownerGotHit) {
        this.ownerGotHit = ownerGotHit;
    }
}


