package com.example.tanksjava.gamewindow.gameobjects.game_objects;


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

    public void addEnemyTanksInBulk(TankGameObject tank, int numberOfTanks, int startingPositionX, int startingPositionY, int separatorX){
        for (int i=0;i<numberOfTanks;i++){
            tank.setInitialPositionAndRotation(0,startingPositionX+(separatorX*i),startingPositionY);
            tankList.add(new TankGameObject(tank));
        }

    }

    public void initiateTanks(Pane pane){
        for(Iterator<TankGameObject> s = tankList.iterator(); s.hasNext();) {
            TankGameObject temptank = s.next();
            temptank.tankMovementInitialization(pane);
        }
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
                StaticToolsAndHandlers.explosionHandler(temptank);
                StaticToolsAndHandlers.clearObjectHitBox(temptank.getTankDirectionController(),temptank.getHitBoxController());
                pane.getChildren().remove(temptank.getObjectGraphics());
            }

            if (temptank.getTankShells().getShellList().isEmpty() && temptank.getTankDirectionController().getOwnerGotHit()){
                s.remove();
            }
        }
    }


    public List<TankGameObject> getTankList() {
        return tankList;
    }


}
