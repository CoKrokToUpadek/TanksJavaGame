package com.example.tanksjava.toolsmethods;

import com.example.tanksjava.gamewindow.HitBoxController;
import com.example.tanksjava.gamewindow.PlayerController;
import com.example.tanksjava.gamewindow.gameobjects.PlayerControlledGameObject;
import com.example.tanksjava.gamewindow.gameobjects.StaticGameObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;

public class StaticToolsAndHandlers {

    public enum itemOrientation{
        HORIZONTAL,VERTICAL
    }
    public static void setBackGround(Pane pane, String url, int sizeX, int sizeY){
        BackgroundImage myBI= new BackgroundImage(new Image(url,sizeX,sizeY,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
    }


    public static void musicAndSoundHandler(MediaPlayer mediaPlayer, boolean isLooped){
        if(!mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            if (isLooped){
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
            mediaPlayer.setVolume(0.05);
            mediaPlayer.play();
        }
    }

    public static void insertObjectOnToPane(Pane pane,StaticGameObject object, int objectStartingPositionX, int objectStartingPositionY, int initialRotation, HitBoxController hitBoxController){
        pane.getChildren().add(object.getObjectGraphics());
        object.getObjectGraphics().setLayoutX(objectStartingPositionX);
        object.getObjectGraphics().setLayoutY(objectStartingPositionY);
        object.getObjectGraphics().setRotate(initialRotation);
        hitBoxController.fillHitBoxArrayWithFlags(object.getObjectFlag(),objectStartingPositionX,objectStartingPositionY,object.getObjectSizeX(),object.getObjectSizeY());

    }

     public  static  void updatePlayerHitBox(int flag,PlayerControlledGameObject object, HitBoxController hitBoxController){
       // System.out.println("i load "+flag+" now");
        hitBoxController.fillHitBoxArrayWithFlags(flag,object.getPc().getCurrentPositionX(),object.getPc().getCurrentPositionY(),object.getObjectSizeX(),object.getObjectSizeY());

    }



    public static void addStaticObjectsInBulk(Pane pane,StaticGameObject object, int numberOfObjects, int statingPositionX, int startingPositionY, StaticToolsAndHandlers.itemOrientation orientation,HitBoxController hitBoxController){

        int sizeX=0;
        int sizeY=0;

        for (int i=0;i<numberOfObjects;i++){
            if (orientation.equals(StaticToolsAndHandlers.itemOrientation.HORIZONTAL)){
                StaticToolsAndHandlers.insertObjectOnToPane(pane,new StaticGameObject(object) ,statingPositionX+sizeX,startingPositionY,0,hitBoxController);
                sizeX+=object.getObjectSizeX();
            }else {
                StaticToolsAndHandlers.insertObjectOnToPane(pane,new StaticGameObject(object),statingPositionX,startingPositionY+sizeY,0,hitBoxController);
                sizeY+=object.getObjectSizeY();
            }

        }

    }


}
