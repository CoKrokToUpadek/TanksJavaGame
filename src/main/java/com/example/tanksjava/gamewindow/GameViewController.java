package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.PlayerControlledGameObject;
import com.example.tanksjava.gamewindow.gameobjects.StaticGameObject;
import com.example.tanksjava.gamewindow.gameobjects.URLStringsOfAssets;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;


public class GameViewController {
    @FXML
    public AnchorPane newGamePane;

    //must be global otherwise garbage collector removes it after 1 second
    private MediaPlayer backgroundMusic;


    public AnimationTimer gameLoop;


   private final int playerStartingPosX =30;
   private final int playerStartingPosY =85;

   private final int gameBoardSizeX=800;
   private final int gameBoardSizeY=600;

   HitBoxController hitBoxController=new HitBoxController(gameBoardSizeX,gameBoardSizeY);

   private final StaticGameObject crate=new StaticGameObject(1,URLStringsOfAssets.metalBoxGraphicAsset,28,28,80,
           80,false,0,hitBoxController);



    PlayerControlledGameObject player1=new PlayerControlledGameObject(2,URLStringsOfAssets.playerTankGraphicAsset,52,52,
     playerStartingPosX, playerStartingPosY,true,180, hitBoxController);


    @FXML
    private void initialize(){
        //background settings
        StaticToolsAndHandlers.setBackGround(newGamePane,URLStringsOfAssets.gameBoardGraphicAsset,gameBoardSizeX,gameBoardSizeY);
        backgroundMusic = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.backGroundMusicAsset).toUri().toString()));
        StaticToolsAndHandlers.musicAndSoundHandler(backgroundMusic,true);
        startGameLoop();

        //program doesn't work without it
        Button button=new Button();
        button.setVisible(true);
        newGamePane.getChildren().add(button);

        player1.playerMovementInitialization(newGamePane);

        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane,crate,28,0,0, StaticToolsAndHandlers.itemOrientation.HORIZONTAL,hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane,crate,28,0,572, StaticToolsAndHandlers.itemOrientation.HORIZONTAL,hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane,crate,21,0,0, StaticToolsAndHandlers.itemOrientation.VERTICAL,hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane,crate,21,772,0, StaticToolsAndHandlers.itemOrientation.VERTICAL,hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane,crate,6,200,200, StaticToolsAndHandlers.itemOrientation.VERTICAL,hitBoxController);
       // hitBoxController.printHitBoxArray();
    }


    public void startGameLoop(){
        gameLoop =new AnimationTimer() {
            @Override
            public void handle(long now) {
                player1.tankPositionAndOrientationUpdater();
            }
        };
        gameLoop.start();
    }

}
