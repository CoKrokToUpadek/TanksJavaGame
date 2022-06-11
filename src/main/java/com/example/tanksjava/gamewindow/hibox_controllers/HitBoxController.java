package com.example.tanksjava.gamewindow.hibox_controllers;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.ObjectDirectionController;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.StaticGameObject;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;

public class HitBoxController {

    //flags are: 1 for player object, 2 for indestructible static objects
    private final Flag[][] gameBoardHitBoxArray;
    private final int gameBoardSizeX;
    private final int gameBoardSizeY;


    public HitBoxController(int gameBoardSizeX, int gameBoardSizeY) {
        this.gameBoardSizeX = gameBoardSizeX;
        this.gameBoardSizeY = gameBoardSizeY;
        gameBoardHitBoxArray = new Flag[gameBoardSizeY][gameBoardSizeX];
        fillHitBoxArrayWithFlags(new Flag(0), 0, 0, gameBoardSizeX, gameBoardSizeY);

    }


    public void fillHitBoxArrayWithFlags(Flag flag, int startingPointX, int startingPointY, int objectSizeX, int objectSizeY) {
        for (int i = startingPointY; i < objectSizeY + startingPointY; i++) {
            for (int j = startingPointX; j < startingPointX + objectSizeX; j++) {
                gameBoardHitBoxArray[i][j] = flag;
            }
        }
    }


    public int detectCollisionsForMovement(ObjectDirectionController playerObject, int direction, int speed) {

        //w-up
        //s-down
        //a-left
        //d-right

        int objectCollisionPoint;
        int objectStartPlusSizeX = playerObject.getCurrentPositionX() + playerObject.getObjectSizeX();
        int objectStartPlusSizeY = playerObject.getCurrentPositionY() + playerObject.getObjectSizeY();
        int objectStartY = playerObject.getCurrentPositionY();
        int objectStartX = playerObject.getCurrentPositionX();


        switch (direction) {
            case 180:
                objectCollisionPoint = objectStartY - speed;
                for (int i = objectStartX; i < objectStartPlusSizeX; i++) {
                    if (gameBoardHitBoxArray[objectCollisionPoint][i].getFlagValue() != 0) {
                        speed = flagAndSpeedHandler(playerObject, gameBoardHitBoxArray[objectCollisionPoint][i], 180, speed);

                    }
                }
                break;
            case 0:
                objectCollisionPoint = objectStartPlusSizeY + speed;
                for (int i = objectStartX; i < objectStartPlusSizeX; i++) {//ok
                    if (gameBoardHitBoxArray[objectCollisionPoint][i].getFlagValue() != 0) {
                        speed = flagAndSpeedHandler(playerObject, gameBoardHitBoxArray[objectCollisionPoint][i], 0, speed);


                    }
                }
                break;

            case 90:
                objectCollisionPoint = objectStartX - speed;
                for (int i = objectStartY; i < objectStartPlusSizeY; i++) {
                    if (gameBoardHitBoxArray[i][objectCollisionPoint].getFlagValue() != 0) {
                        speed = flagAndSpeedHandler(playerObject, gameBoardHitBoxArray[i][objectCollisionPoint], 90, speed);

                    }
                }

                break;
            case 270:
                objectCollisionPoint = objectStartPlusSizeX + speed;
                for (int i = objectStartY; i < objectStartPlusSizeY; i++) {
                    if (gameBoardHitBoxArray[i][objectCollisionPoint].getFlagValue() != 0) {
                        speed = flagAndSpeedHandler(playerObject, gameBoardHitBoxArray[i][objectCollisionPoint], 270, speed);

                    }
                }
                break;
        }
        return speed;
    }

    //TODO-rest of interactions
    private int flagAndSpeedHandler(ObjectDirectionController playerObject, Flag targetFlag, int direction, int speed) {

        int outputSpeed = 0;

        int objectStartPlusSizeX = playerObject.getCurrentPositionX() + playerObject.getObjectSizeX();
        int objectStartPlusSizeY = playerObject.getCurrentPositionY() + playerObject.getObjectSizeY();
        int objectStartY = playerObject.getCurrentPositionY();
        int objectStartX = playerObject.getCurrentPositionX();


        StaticGameObject owner = gameBoardHitBoxArray[objectStartY+4][objectStartX+1].getOwner();


        switch (targetFlag.getFlagValue()) {
            case 1:
                if (targetFlag.getOwner().isDestructible() && owner != null && owner.getObjectFlag().getFlagValue() == 3) {//3 is a flag for missile, missile logic detect slowing down before object as hit
                    StaticToolsAndHandlers.staticObjectRemover(targetFlag.getOwner(), this);
                    return speed - 1;
                }
                outputSpeed = flagAndSpeedHandlerInternalLogicForSlowingDown(objectStartY, objectStartX, objectStartPlusSizeY, objectStartPlusSizeX, direction, speed, 1);
                break;

            case 2:
                if (targetFlag.getOwner().isDestructible() && owner != null && (owner.getObjectFlag().getFlagValue() == 3 || owner.getObjectFlag().getFlagValue() == 2)) {
                    return speed;
                } else {
                    outputSpeed = flagAndSpeedHandlerInternalLogicForSlowingDown(objectStartY, objectStartX, objectStartPlusSizeY, objectStartPlusSizeX, direction, speed, 2);
                }
                break;
            case 3:
                break;

            case 4:
                outputSpeed = flagAndSpeedHandlerInternalLogicForSlowingDown(objectStartY, objectStartX, objectStartPlusSizeY, objectStartPlusSizeX, direction, speed, 4);
                break;

        }
        return outputSpeed;
    }

    private int flagAndSpeedHandlerInternalLogicForSlowingDown(int objectStartY, int objectStartX, int objectStartPlusSizeY, int objectStartPlusSizeX, int direction, int speed, int slowFlag) {
        int step = 0;
        switch (direction) {
            case 0:
                while (step <= speed) {
                    for (int k = objectStartX; k < objectStartPlusSizeX; k++) {
                        if (gameBoardHitBoxArray[objectStartPlusSizeY + step][k].getFlagValue() == slowFlag) {
                            return step;
                        }
                    }
                    step++;
                }
                break;
            case 90:
                while (step <= speed) {
                    for (int k = objectStartY; k < objectStartPlusSizeY; k++) {
                        if (gameBoardHitBoxArray[k][objectStartX - step].getFlagValue() == slowFlag) {
                            if (step == 1) {
                                return 0;
                            }
                            return step - 1;
                        }
                    }
                    step++;
                }
                break;
            case 180:
                while (step <= speed) {
                    for (int k = objectStartX; k < objectStartPlusSizeX; k++) {//ok
                        if (gameBoardHitBoxArray[objectStartY - step][k].getFlagValue() == slowFlag) {
                            if (step == 1) {
                                return 0;
                            }
                            return step - 1;
                        }
                    }
                    step++;
                }
                break;
            case 270:
                while (step <= speed) {
                    for (int k = objectStartY; k < objectStartPlusSizeY; k++) {
                        if (gameBoardHitBoxArray[k][objectStartPlusSizeX + step].getFlagValue() == slowFlag) {
                            return step;
                        }
                    }
                    step++;
                }
                break;
        }
        return speed;

    }


    public void printSinglePoint(int x, int y) {
        System.out.println("current value under point" + gameBoardHitBoxArray[y][x].getFlagValue());
    }

    //divider added for printing smaller chunks of array

    public void printHitBoxArray(int divider) {

        if (divider < 1) {
            divider = 1;
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (int i = 0; i < gameBoardSizeY / divider; i++) {
            for (int j = 0; j < gameBoardSizeX / divider; j++) {
                System.out.print(gameBoardHitBoxArray[i][j].getFlagValue());
            }
            System.out.println();
        }
    }


}
