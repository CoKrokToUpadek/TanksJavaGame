package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.PlayerControlledGameObject;

public class HitBoxController {

    //flags are: 1 for player object, 2 for indestructible static objects
    private int [][] gameBoardHitBoxArray;
    private final int gameBoardSizeX;
    private final int gameBoardSizeY;

    public HitBoxController(int gameBoardSizeX, int gameBoardSizeY) {
        this.gameBoardSizeX = gameBoardSizeX;
        this.gameBoardSizeY = gameBoardSizeY;
        gameBoardHitBoxArray=new int[gameBoardSizeY][gameBoardSizeX];

    }

    public int[][] getGameBoardHitBoxArray() {
        return gameBoardHitBoxArray;
    }

    public void fillHitBoxArrayWithFlags(int flag, int startingPointX, int startingPointY, int objectSizeX, int objectSizeY){
        for(int i=startingPointY;i<objectSizeY+startingPointY;i++){
            for (int j=startingPointX;j<startingPointX+objectSizeX;j++){
                gameBoardHitBoxArray[i][j]=flag;
            }
        }
    }

    public int detectCollisionsForMovement(PlayerController playerObject, int collideFlag, char direction, int speed){

        int outPutSpeed=0;
         //w-up
         //s-down
         //a-left
         //d-right
        switch (direction){
            case 'w':
                for (int i=playerObject.getCurrentPositionX();i<playerObject.getCurrentPositionX()+ playerObject.getObjectSizeX();i++){
                        if (gameBoardHitBoxArray[playerObject.getCurrentPositionY()-speed][i]== collideFlag){
                            //need to fill new speed
                            return outPutSpeed;
                        }
                }
                break;
            case 's':
                for (int i=playerObject.getCurrentPositionX();i<playerObject.getCurrentPositionX()+ playerObject.getObjectSizeX();i++){
                    if (gameBoardHitBoxArray[playerObject.getCurrentPositionY()+ playerObject.getObjectSizeY()+speed][i]== collideFlag){
                        //need to fill new speed
                          return outPutSpeed;
                    }
                }
                break;

            case 'a':
                for (int i=playerObject.getCurrentPositionY();i<playerObject.getCurrentPositionY()+ playerObject.getObjectSizeY();i++){
                    if (gameBoardHitBoxArray[i][playerObject.getCurrentPositionX()-speed]== collideFlag){
                        //need to fill new speed
                        return outPutSpeed;
                    }
                }

                break;
            case 'd':
                for (int i=playerObject.getCurrentPositionY();i<playerObject.getCurrentPositionY()+ playerObject.getObjectSizeY();i++){
                    if (gameBoardHitBoxArray[i][playerObject.getCurrentPositionX()+ playerObject.getObjectSizeX()+speed]== collideFlag){
                        outPutSpeed=
                        return outPutSpeed;
                    }
                }
                break;


        }
        return speed;
    }



    private int speedReductorBeforeCollison(PlayerController playerObject, char direction, int speed, int collideFlag){

        int objectStaringPosition=0;
        int objectSize=0;
        int objectSpeed=0;
        int startingPoint=0;
        int collisionPoint=0;
        int step;



        switch (direction){
            case 'w':
                objectStaringPosition=playerObject.getCurrentPositionY();
                objectSize=0;
                objectSpeed=-speed;


                break;
            case 's':
                objectStaringPosition=playerObject.getCurrentPositionY();
                objectSize=playerObject.getObjectSizeY();
                objectSpeed=speed;

                break;
            case 'a':
                objectStaringPosition=playerObject.getCurrentPositionX();
                objectSize=0;
                objectSpeed=-speed;

                break;

            case 'd':
                objectStaringPosition=playerObject.getCurrentPositionX();
                objectSize=playerObject.getObjectSizeX();
                objectSpeed=speed;
                break;
        }
        startingPoint=objectStaringPosition+objectSize;
        collisionPoint=startingPoint+objectSpeed;
        for (int i=startingPoint;i<collisionPoint;i++){
            if(gameBoardHitBoxArray[i][]== collideFlag){
                return step;
            }
            step++;
        }
    }

    public void printSinglePoint(int x, int y){
        System.out.println("current value under point"+gameBoardHitBoxArray[y][x]);
    }

    //divider added for printing smaller chunks of array

    public void printHitBoxArray(int divider){

        if (divider<1) {
            divider = 1;
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (int i=0;i<gameBoardSizeY/divider;i++){
            for (int j=0;j<gameBoardSizeX/divider;j++){
                System.out.print(gameBoardHitBoxArray[i][j]);
            }
            System.out.println();
        }
    }


}
