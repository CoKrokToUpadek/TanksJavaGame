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
                        //need to fill new speed
                        return outPutSpeed;
                    }
                }
                break;


        }
        return speed;
    }

    public void printSinglePoint(int x, int y){
        System.out.println("current value under point"+gameBoardHitBoxArray[y][x]);
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

    public void printObjectCorners(PlayerController object){


        int x=object.getCurrentPositionX();
        int y=object.getCurrentPositionY();

        int flagValue=gameBoardHitBoxArray[y][x];
        int flagValuePlusSize=gameBoardHitBoxArray[y][x+object.getObjectSizeX()-1];

        int xPlusSize=object.getCurrentPositionX()+object.getObjectSizeX()-1;
        int yPlusSize=object.getCurrentPositionY()+object.getObjectSizeY()-1;

        int of1flagValue=gameBoardHitBoxArray[y][x-1];
        int of1flagValuePlusSize=gameBoardHitBoxArray[y][xPlusSize+1];

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("printing each corner of object:");
        System.out.println("         x-1="+(x-1)+"                             "+("x+1="+(xPlusSize+1)));
        System.out.println("y="+(y)+"|flagVal-1:"+of1flagValue+"|                     |flagVal+1:"+of1flagValuePlusSize+("|y="+(y)));
        System.out.println("___________________________________________________________________________");
        System.out.println("                x="+x+"                   "+("x="+xPlusSize));
        System.out.println("          y="+y+"|flagVal:"+flagValue+"|           |flagVal:"+flagValuePlusSize+("|y="+y));
        System.out.println();
        System.out.println();
        System.out.println();

        flagValue=gameBoardHitBoxArray[yPlusSize-1][x];
        flagValuePlusSize=gameBoardHitBoxArray[yPlusSize-1][xPlusSize-1];

        of1flagValue=gameBoardHitBoxArray[yPlusSize][x-1];
        of1flagValuePlusSize=gameBoardHitBoxArray[yPlusSize][xPlusSize+1];

        System.out.println("          y="+yPlusSize+"|flagVal:"+flagValue+"|           |flagVal:"+flagValuePlusSize+("|y="+yPlusSize));
        System.out.println("                x="+x+"                   "+("x="+xPlusSize));
        System.out.println("___________________________________________________________________________");
        System.out.println("y="+(yPlusSize)+"|flagVal-1:"+of1flagValue+"|                     |flagVal+1:"+of1flagValuePlusSize+("|y="+(yPlusSize)));
        System.out.println("         x-1="+(x+1)+"                             "+("x+1="+(xPlusSize+1)));
    }


}
