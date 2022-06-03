package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.PlayerControlledGameObject;
import com.example.tanksjava.gamewindow.gameobjects.StaticGameObject;

import java.util.Arrays;

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
                        //need to fill new speed
                        return outPutSpeed;
                    }
                }
                break;


        }
        return speed;
    }




    public void printHitBoxArray(){

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (int i=0;i<gameBoardSizeY;i++){
            for (int j=0;j<gameBoardSizeX;j++){
                System.out.print(gameBoardHitBoxArray[i][j]);
            }
            System.out.println();
        }
    }


}
