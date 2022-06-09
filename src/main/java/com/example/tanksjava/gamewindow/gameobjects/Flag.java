package com.example.tanksjava.gamewindow.gameobjects;

public class Flag {
    int flagValue;
    StaticGameObject object;

    public Flag(int flagValue) {
        this.flagValue = flagValue;
    }

    public void setObject(StaticGameObject object) {
        this.object = object;
    }
}
