package com.example.tanksjava.gamewindow.hibox_controllers;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.StaticGameObject;



public class Flag {

    int flagValue;
     StaticGameObject object;

    public Flag(int flagValue) {
        this.flagValue = flagValue;
        object=null;
    }
    public void setObject(StaticGameObject object) {
        this.object = object;
    }

    public int getFlagValue() {
        return flagValue;
    }
    public StaticGameObject getOwner() {
        return object;
    }
}
