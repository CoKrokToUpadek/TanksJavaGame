package com.example.tanksjava.gamewindow.gameobjects;

import com.example.tanksjava.gamewindow.PlayerController;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class PlayerControlledGameObject extends StaticGameObject{

    private final PlayerController pc;
    private char inputForTankSteering;

    private final MediaPlayer tankEngineSound;

    public PlayerControlledGameObject(String objectURLString, int pixelSizeX, int pixelSizeY, int objectStartingPositionX,
                                      int objectStartingPositionY, boolean isDestructible, int initialRotation) {
        super(objectURLString, pixelSizeX, pixelSizeY, objectStartingPositionX, objectStartingPositionY, isDestructible,initialRotation);
        pc=new PlayerController(objectStartingPositionX,objectStartingPositionY,pixelSizeX,pixelSizeY,4);
        tankEngineSound=new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.tankSoundMusicAsset).toUri().toString()));
    }


    public void playerMovementInitialization(Pane pane){

        insertObjectOnToPane(pane);

        pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                playerEngineSoundHandler();
                inputForTankSteering=event.getCharacter().charAt(0);
            }
        });
    }


    public void tankPositionAndOrientationUpdater(){

        pc.updatePlayerCurrentPosition(inputForTankSteering);
        pc.playerRotation(inputForTankSteering);
        getObjectGraphics().setRotate(pc.getPlayerRotation());
        getObjectGraphics().setLayoutY(pc.getCurrentPositionY());
        getObjectGraphics().setLayoutX(pc.getCurrentPositionX());
        System.out.println(pc.getCurrentPositionX());
        System.out.println(pc.getCurrentPositionY());
        System.out.println( getObjectGraphics().getRotate());
        //clear input for the next frame
        this.inputForTankSteering='x';
    }

    public void playerEngineSoundHandler(){

        StaticToolsAndHandlers.musicAndSoundHandler(tankEngineSound,false);
        tankEngineSound.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                tankEngineSound.stop();
            }
        });
        if (tankEngineSound.getStatus().equals(MediaPlayer.Status.STOPPED)){
            tankEngineSound.play();
        }

        System.out.println(tankEngineSound.getStatus());
    }

    public char getInputForTankSteering() {
        return inputForTankSteering;
    }
}
