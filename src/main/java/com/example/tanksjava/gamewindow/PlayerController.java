package com.example.tanksjava.gamewindow;

import com.example.tanksjava.mainmenuwindow.MainMenuController;


public class PlayerController {
    private int currentPositionX;
    private int currentPositionY;

    private int currentBarrelPositionX;
    private int currentBarrelPositionY;


    private int playerRotation;

    private final int objectSizeX;
    private final int objectSizeY;

    private final int objectSpeed;

    private HitBoxController hitBoxController;

    public PlayerController(int startingPositionX, int staringPositionY, int objectSizeX, int objectSizeY, int objectSpeed,int initialRotation , HitBoxController hitBoxController) {
        this.currentPositionY = staringPositionY;
        this.currentPositionX = startingPositionX;
        this.objectSizeX = objectSizeX;
        this.objectSizeY = objectSizeY;
        this.objectSpeed = objectSpeed;
        this.hitBoxController = hitBoxController;
        this.playerRotation=initialRotation;

    }


    public void updatePlayerBarrelPosition() {
        switch (playerRotation) {
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

    public void updatePlayerCurrentPosition(char keyboardInput) {

        switch (Character.toLowerCase(keyboardInput)) {
            case 'w':
                if (!(currentPositionY <= 0)) {
                    if (hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed) >= objectSpeed) {
                        currentPositionY -= objectSpeed;
                    } else {
                        currentPositionY -= hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed);
                    }
                }
                break;
            case 's':
                if (!(currentPositionY >= MainMenuController.getGameWindowHeight() - objectSizeY)) {

                    if (hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed) >= objectSpeed) {
                        currentPositionY += objectSpeed;
                    } else {
                        currentPositionY += hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed);
                    }

                }
                break;
            case 'a':
                if (!(currentPositionX <= 0)) {

                    if (hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed) >= objectSpeed) {
                        currentPositionX -= objectSpeed;
                    } else {
                        currentPositionX -= hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed);
                    }


                }
                break;
            case 'd':
                if (!(currentPositionX >= MainMenuController.getGameWindowWidth() - objectSizeX)) {

                    if (hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed) >= objectSpeed) {
                        currentPositionX += objectSpeed;
                    } else {
                        currentPositionX += hitBoxController.detectCollisionsForMovement(this, 1, keyboardInput, objectSpeed);
                    }

                }
                break;
            case 'e':
                System.exit(0);
                break;

            case 'r':

                //hitBoxController.printHitBoxArray(2);
//                System.out.println("TankX=" + getCurrentPositionX());
//                System.out.println("BarrelX=" + getCurrentBarrelPositionX());
//                System.out.println();
//                System.out.println("TankY==" + getCurrentPositionY());
//                System.out.println("BarrelY==" + getCurrentBarrelPositionY());


                break;

            default:
                break;
        }
    }

    public void playerRotation(char rotationInput) {

        switch (Character.toLowerCase(rotationInput)) {
            case 'w':
                playerRotation = 180;
                break;
            case 's':
                playerRotation = 0;
                break;
            case 'a':
                playerRotation = 90;
                break;
            case 'd':
                playerRotation = 270;
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

    public int getPlayerRotation() {
        return playerRotation;
    }

    public int getCurrentBarrelPositionX() {return currentBarrelPositionX;}

    public int getCurrentBarrelPositionY() {return currentBarrelPositionY;}
}
