package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.PlayerControlledGameObject;
import com.example.tanksjava.gamewindow.gameobjects.StaticGameObject;
import com.example.tanksjava.gamewindow.gameobjects.URLStringsOfAssets;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;


public class GameViewController {
    @FXML
    public AnchorPane newGamePane;
    public AnimationTimer gameLoop;

   private final int playerStartingPosX =400;
   private final int playerStartingPosY =40;

   private final StaticGameObject crate=new StaticGameObject(URLStringsOfAssets.metalBoxGraphicAsset,28,28,80,
           80,false,0);



    PlayerControlledGameObject player1=new PlayerControlledGameObject(URLStringsOfAssets.playerTankGraphicAsset,52,52,
     playerStartingPosX, playerStartingPosY,true,200);


    @FXML
    private void initialize(){
        //background settings
        StaticToolsAndHandlers.setBackGround(newGamePane,URLStringsOfAssets.gameBoardGraphicAsset,800,800);
        MediaPlayer backgroundMusic = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.backGroundMusicAsset).toUri().toString()));
        StaticToolsAndHandlers.musicAndSoundHandler(backgroundMusic,true);



        startGameLoop();

        //program doesnt work without it
        Button button=new Button();
        button.setVisible(true);
        newGamePane.getChildren().add(button);

        crate.insertObjectOnToPane(newGamePane);
        player1.playerMovementInitialization(newGamePane);

    }


    public void startGameLoop(){
        gameLoop =new AnimationTimer() {
            @Override
            public void handle(long now) {
                player1.tankPositionAndOrientationUpdater();
//
            }
        };
        gameLoop.start();
    }

}
