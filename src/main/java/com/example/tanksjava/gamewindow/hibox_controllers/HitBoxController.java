package com.example.tanksjava.gamewindow.hibox_controllers;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.ObjectDirectionController;
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
        fillHitBoxArrayWithFlags(new Flag(0),0,0,gameBoardSizeX,gameBoardSizeY);

    }


    public void fillHitBoxArrayWithFlags(Flag flag, int startingPointX, int startingPointY, int objectSizeX, int objectSizeY) {
        for (int i = startingPointY; i < objectSizeY + startingPointY; i++) {
            for (int j = startingPointX; j < startingPointX + objectSizeX; j++) {
                gameBoardHitBoxArray[i][j] = flag;
            }
        }
    }

    public int detectCollisionsForMovementOfNonDestructibleObject(ObjectDirectionController playerObject, int collideFlag, int direction, int speed) {

        //w-up
        //s-down
        //a-left
        //d-right

        int step = 0;
        int objectCollisionPoint = 0;
        int objectStartPlusSizeX = playerObject.getCurrentPositionX() + playerObject.getObjectSizeX();
        int objectStartPlusSizeY = playerObject.getCurrentPositionY() + playerObject.getObjectSizeY();
        int objectStartY = playerObject.getCurrentPositionY();
        int objectStartX = playerObject.getCurrentPositionX();


        switch (direction) {
            case 180:
                objectCollisionPoint = objectStartY - speed;
                for (int i = objectStartX; i < objectStartPlusSizeX; i++) {
                    if (gameBoardHitBoxArray[objectCollisionPoint][i].getFlagValue() == collideFlag) {
                        if (gameBoardHitBoxArray[objectCollisionPoint][i].getOwner().isDestructible()){
                            StaticToolsAndHandlers.clearStaticHitBox( gameBoardHitBoxArray[objectCollisionPoint][i].getOwner(),this);
                            return speed-1;
                        }else{
                            while (step <= speed) {
                                for (int k = objectStartX; k < objectStartPlusSizeX; k++) {//ok
                                    if (gameBoardHitBoxArray[objectStartY - step][k].getFlagValue() == collideFlag) {
                                        if (step == 1) {
                                            return 0;
                                        }
                                        return step - 1;
                                    }
                                }
                                step++;
                            }

                        }

                    }
                }
                break;
            case 0:
                objectCollisionPoint = objectStartPlusSizeY + speed;
                for (int i = objectStartX; i < objectStartPlusSizeX; i++) {//ok
                    if (gameBoardHitBoxArray[objectCollisionPoint][i].getFlagValue() == collideFlag) {
                        if (gameBoardHitBoxArray[objectCollisionPoint][i].getOwner().isDestructible()){
                            StaticToolsAndHandlers.clearStaticHitBox( gameBoardHitBoxArray[objectCollisionPoint][i].getOwner(),this);
                            return speed-1;
                        }else{
                            while (step <= speed) {
                                for (int k = objectStartX; k < objectStartPlusSizeX; k++) {//ok
                                    if (gameBoardHitBoxArray[objectStartPlusSizeY + step][k].getFlagValue() == collideFlag) {
                                        return step;
                                    }
                                }
                                step++;
                            }
                        }
                    }
                }
                break;

            case 90:
                objectCollisionPoint = objectStartX - speed;
                for (int i = objectStartY; i < objectStartPlusSizeY; i++) {
                    if (gameBoardHitBoxArray[i][objectCollisionPoint].getFlagValue() == collideFlag) {
                        if (gameBoardHitBoxArray[i][objectCollisionPoint].getOwner().isDestructible()){
                            StaticToolsAndHandlers.clearStaticHitBox( gameBoardHitBoxArray[i][objectCollisionPoint].getOwner(),this);
                            return speed-1;
                        }else{
                            while (step <= speed) {
                                for (int k = objectStartY; k < objectStartPlusSizeY; k++) {
                                    if (gameBoardHitBoxArray[k][objectStartX - step].getFlagValue() == collideFlag) {
                                        if (step == 1) {
                                            return 0;
                                        }
                                        return step - 1;
                                    }
                                }
                                step++;
                            }
                        }
                    }
                }

                break;
            case 270:
                objectCollisionPoint = objectStartPlusSizeX + speed;
                for (int i = objectStartY; i < objectStartPlusSizeY; i++) {
                    if (gameBoardHitBoxArray[i][objectCollisionPoint].getFlagValue() == collideFlag) {
                        if (gameBoardHitBoxArray[i][objectCollisionPoint].getOwner().isDestructible()){
                            StaticToolsAndHandlers.clearStaticHitBox( gameBoardHitBoxArray[i][objectCollisionPoint].getOwner(),this);
                            return speed-1;

                        }else {
                            while (step <= speed) {
                                for (int k = objectStartY; k < objectStartPlusSizeY; k++) {
                                    if (gameBoardHitBoxArray[k][objectStartPlusSizeX + step].getFlagValue() == collideFlag) {
                                        return step;
                                    }
                                }
                                step++;
                            }
                        }

                    }
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
