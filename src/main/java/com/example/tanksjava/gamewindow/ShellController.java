package com.example.tanksjava.gamewindow;

public class ShellController extends ObjectDirectionController {
    public ShellController(int startingPositionX, int staringPositionY, int objectSizeX, int objectSizeY, int objectSpeed,int initialRotation ,HitBoxController hitBoxController) {
        super(startingPositionX, staringPositionY, objectSizeX, objectSizeY, objectSpeed,initialRotation,hitBoxController);
    }
}
