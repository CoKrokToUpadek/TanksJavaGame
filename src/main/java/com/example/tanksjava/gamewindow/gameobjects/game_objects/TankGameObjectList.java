package com.example.tanksjava.gamewindow.gameobjects.game_objects;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.shells.ShellGameObject;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankGameObjectList {

   private final List<TankGameObject> tankList;

    public TankGameObjectList() {
        tankList=new ArrayList<>();
    }

    public void addNewTankToList(TankGameObject tank){
        tankList.add(tank);
    }

    public void removeTankFromTheList(TankGameObject tank){
        tankList.remove(tank);
    }

    public void updateTanksPosition(long counter, Pane pane){
        for(Iterator<TankGameObject> s = tankList.iterator(); s.hasNext();){
            TankGameObject temptank=s.next();
            temptank.tankPositionAndOrientationUpdater(counter);
            if (!temptank.getTankShells().getShellList().isEmpty()) {
                temptank.getTankShells().shellListPositionUpdate(pane);
            }

            if (temptank.getTankDirectionController().getOwnerGotHit()){
                StaticToolsAndHandlers.clearObjectHitBox(temptank.getTankDirectionController(),temptank.getHitBoxController());
                pane.getChildren().remove(temptank.getObjectGraphics());
            }
        }
    }


    public List<TankGameObject> getTankList() {
        return tankList;
    }
}
