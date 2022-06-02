package com.example.tanksjava.toolsmethods;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;

public class StaticToolsAndHandlers {


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



}
