package com.example.tanksjava.gamewindow;

public class HitBoxController {

    //flags are: 1 for player object, 2 for indestructible static objects
    private int[][] gameBoardHitBoxArray;
    private final int gameBoardSizeX;
    private final int gameBoardSizeY;

    public HitBoxController(int gameBoardSizeX, int gameBoardSizeY) {
        this.gameBoardSizeX = gameBoardSizeX;
        this.gameBoardSizeY = gameBoardSizeY;
        gameBoardHitBoxArray = new int[gameBoardSizeY][gameBoardSizeX];

    }

    public int[][] getGameBoardHitBoxArray() {
        return gameBoardHitBoxArray;
    }

    public void fillHitBoxArrayWithFlags(int flag, int startingPointX, int startingPointY, int objectSizeX, int objectSizeY) {
        for (int i = startingPointY; i < objectSizeY + startingPointY; i++) {
            for (int j = startingPointX; j < startingPointX + objectSizeX; j++) {
                gameBoardHitBoxArray[i][j] = flag;
            }
        }
    }

    public int detectCollisionsForMovement(PlayerController playerObject, int collideFlag, char direction, int speed) {

        //w-up
        //s-down
        //a-left
        //d-right
        int outPutSpeed = 0;
        int step = 0;
        int objectCollisionPoint = 0;
        int objectStartPlusSizeX = playerObject.getCurrentPositionX() + playerObject.getObjectSizeX();
        int objectStartPlusSizeY = playerObject.getCurrentPositionY() + playerObject.getObjectSizeY();
        int objectStartY = playerObject.getCurrentPositionY();
        int objectStartX = playerObject.getCurrentPositionX();


        switch (direction) {
            case 'w':
                objectCollisionPoint = objectStartY - speed;

                for (int i = objectStartX; i < objectStartPlusSizeX; i++) {
                    if (gameBoardHitBoxArray[objectCollisionPoint][i] == collideFlag) {

                        while (step <= speed) {
                            for (int k = objectStartX; k < objectStartPlusSizeX; k++) {//ok
                                if (gameBoardHitBoxArray[objectStartY - step][k] == collideFlag) {
                                    System.out.println(step);
                                    if (step==1){
                                        return 0;
                                    }
                                    return step-1;
                                }
                            }
                            step++;
                        }
                    }
                }
                break;
            case 's':
                objectCollisionPoint = objectStartPlusSizeY + speed;


                for (int i = objectStartX; i < objectStartPlusSizeX; i++) {//ok
                    if (gameBoardHitBoxArray[objectCollisionPoint][i] == collideFlag) {
                        System.out.println("collision detected");
                        while (step <= speed) {
                            for (int k = objectStartX; k < objectStartPlusSizeX; k++) {//ok
                                if (gameBoardHitBoxArray[objectStartPlusSizeY + step][k] == collideFlag) {
                                    System.out.println(step);
                                    return step;
                                }
                            }
                            step++;
                        }
                    }
                }
                break;

            case 'a':
                objectCollisionPoint = objectStartX - speed;

                for (int i = objectStartY; i < objectStartPlusSizeY; i++) {
                    if (gameBoardHitBoxArray[i][objectCollisionPoint] == collideFlag) {
                        while (step <= speed) {
                            for (int k = objectStartY; k < objectStartPlusSizeY; k++) {
                                if (gameBoardHitBoxArray[k][objectStartX - step] == collideFlag) {
                                    System.out.println(step);
                                    if (step == 1) {
                                        return 0;
                                    }
                                    return step-1;
                                }
                            }
                            step++;
                        }
                    }
                }

                break;
            case 'd':
                objectCollisionPoint = objectStartPlusSizeX + speed;

                for (int i = objectStartY; i < objectStartPlusSizeY; i++) {
                    if (gameBoardHitBoxArray[i][objectCollisionPoint] == collideFlag) {
                        while (step <= speed) {
                            for (int k = objectStartY; k < objectStartPlusSizeY; k++) {
                                if (gameBoardHitBoxArray[k][objectStartPlusSizeX + step] == collideFlag) {
                                    System.out.println(step);

                                    return step;
                                }

                            }
                            step++;
                        }
                    }
                }
                break;


        }
        return speed;
    }


    public void printSinglePoint(int x, int y) {
        System.out.println("current value under point" + gameBoardHitBoxArray[y][x]);
    }

    //divider added for printing smaller chunks of array

    public void printHitBoxArray(int divider) {

        if (divider < 1) {
            divider = 1;
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (int i = 0; i < gameBoardSizeY / divider; i++) {
            for (int j = 0; j < gameBoardSizeX / divider; j++) {
                System.out.print(gameBoardHitBoxArray[i][j]);
            }
            System.out.println();
        }
    }


}
