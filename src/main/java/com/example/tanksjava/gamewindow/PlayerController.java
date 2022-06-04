package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.StaticGameObject;
import com.example.tanksjava.mainmenuwindow.MainMenuController;


public class PlayerController {
    private int currentPositionX;
    private int currentPositionY;

    private int playerRotation;

    private final int objectSizeX;
    private final int objectSizeY;

    private final int objectSpeed;

    private HitBoxController hitBoxController;

    public PlayerController(int startingPositionX, int staringPositionY, int objectSizeX, int objectSizeY, int objectSpeed, HitBoxController hitBoxController) {
        this.currentPositionY=staringPositionY;
        this.currentPositionX=startingPositionX;
        this.objectSizeX=objectSizeX;
        this.objectSizeY=objectSizeY;
        this.objectSpeed=objectSpeed;
        this.hitBoxController=hitBoxController;
    }

    public void updatePlayerCurrentPosition(char keyboardInput){

        switch (Character.toLowerCase(keyboardInput)){
            case 'w':
                if (!(currentPositionY <= 0)){
                    if (hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed)>=objectSpeed){
                        currentPositionY-=objectSpeed;
                    }else{
                        currentPositionY-=hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed);
                    }
                }
                break;
            case 's':
                if (!(currentPositionY>= MainMenuController.getGameWindowHeight()-objectSizeY)) {

                    if (hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed)>=objectSpeed){
                        currentPositionY +=objectSpeed;
                    }else{
                        currentPositionY+=hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed);
                    }

                }
                break;
            case 'a':
                if (!(currentPositionX <= 0)){

                    if (hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed)>=objectSpeed){
                        currentPositionX-=objectSpeed;
                    }else{
                        currentPositionX-=hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed);
                    }


                }
                break;
            case 'd':
                if (!(currentPositionX >= MainMenuController.getGameWindowWidth()-objectSizeX)){

                    if (hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed)>=objectSpeed){
                        currentPositionX+=objectSpeed;
                    }else{
                        currentPositionX+=hitBoxController.detectCollisionsForMovement(this,1,keyboardInput,objectSpeed);
                    }

                }
                break;
            case 'e':
                System.exit(0);
                break;

            case 'r':
                  hitBoxController.printHitBoxArray(4);
                break;

            default:
                break;
        }
    }

    public void playerRotation(char rotationInput){

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
}
