package com.example.tanksjava.gamewindow.gameobjects.game_objects.shells;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShellObjectList {

   private final List<ShellGameObject> shellList;


    public ShellObjectList() {
        this.shellList = new ArrayList<>();
    }

    public void addNewShellToList(ShellGameObject shell){
        shellList.add(shell);
    }

    public void shellListPositionUpdate(Pane pane)  {
        ShellGameObject tempObject;
        for(Iterator<ShellGameObject> s=shellList.iterator(); s.hasNext();){
          tempObject=s.next();
          if ( tempObject.objectPositionAndOrientationUpdater(pane)){
             s.remove();
          }
        }

    }

    public List<ShellGameObject> getShellList() {
        return shellList;
    }
}
