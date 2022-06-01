package com.example.tanksjava.gamewindow;

import com.example.tanksjava.mainmenuwindow.MainMenuController;
import javafx.scene.image.ImageView;

import java.util.Scanner;

public class PlayerController {
    private int currentPositionX;
    private int currentPositionY;

    private int playerRotation;

    public PlayerController(int startingPositionX, int staringPositionY) {
        this.currentPositionY=staringPositionY;
        this.currentPositionX=startingPositionX;
    }

    public void updatePlayerCurrentPosition(char keyboardInput){

        switch (Character.toLowerCase(keyboardInput)){
            case 'w':
                if (!(currentPositionY<=0)){
                    currentPositionY-=2;
                }
                break;
            case 's':
                if (!(currentPositionY>= MainMenuController.getGameWindowHeight()-52) /*52 is tank size in pixels*/) {
                    currentPositionY += 2;
                }
                break;
            case 'a':
                if (!(currentPositionX <= 0)){
                    currentPositionX-=2;
                }
                break;
            case 'd':
                if (!(currentPositionX >= MainMenuController.getGameWindowWidth()-52)){
                    currentPositionX+=2;
                }
                break;
            case 'e':
                System.exit(0);
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
