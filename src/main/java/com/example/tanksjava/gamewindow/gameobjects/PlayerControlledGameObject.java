package com.example.tanksjava.gamewindow.gameobjects;

import com.example.tanksjava.gamewindow.HitBoxController;
import com.example.tanksjava.gamewindow.PlayerController;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class PlayerControlledGameObject extends StaticGameObject {

    private final PlayerController pc;
    private char inputForTankSteering;

    private final MediaPlayer tankEngineSound;



    private final int playerSpeed=4;





    public PlayerControlledGameObject(int objectFlag,String objectURLString, int pixelSizeX, int pixelSizeY, int objectStartingPositionX,
                                      int objectStartingPositionY, boolean isDestructible, int initialRotation, HitBoxController hitBoxController) {


        super(objectFlag,objectURLString, pixelSizeX, pixelSizeY, objectStartingPositionX, objectStartingPositionY, isDestructible, initialRotation,hitBoxController);
        pc = new PlayerController(objectStartingPositionX, objectStartingPositionY, pixelSizeX, pixelSizeY, playerSpeed, hitBoxController);
        tankEngineSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.tankSoundMusicAsset).toUri().toString()));



    }


    public void playerMovementInitialization(Pane pane) {

        insertObjectOnToPane(pane);

        pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                playerEngineSoundHandler();
                inputForTankSteering = event.getCharacter().charAt(0);
                //for testing purpose
               // tankPositionAndOrientationUpdater();


            }
        });
    }


    public void tankPositionAndOrientationUpdater() {

        if (inputForTankSteering == 'w' || inputForTankSteering == 's' || inputForTankSteering == 'a' || inputForTankSteering == 'd' || inputForTankSteering == 'r') {
            StaticToolsAndHandlers.updatePlayerHitBox(0,this,super.getHitBoxController());
            pc.updatePlayerCurrentPosition(inputForTankSteering);
            pc.playerRotation(inputForTankSteering);
            this.getObjectGraphics().setRotate(pc.getPlayerRotation());
            this.getObjectGraphics().setLayoutY(pc.getCurrentPositionY());
            this.getObjectGraphics().setLayoutX(pc.getCurrentPositionX());
            StaticToolsAndHandlers.updatePlayerHitBox(this.getObjectFlag(),this,super.getHitBoxController());
            this.inputForTankSteering = 'x';
            //getHitBoxController().printHitBoxArray(8);
            getHitBoxController().printSinglePoint(pc.getCurrentPositionX(), pc.getCurrentPositionY());
            getHitBoxController().printSinglePoint(pc.getCurrentPositionX()-1, pc.getCurrentPositionY());
        }
    }

    public void playerEngineSoundHandler() {

        StaticToolsAndHandlers.musicAndSoundHandler(tankEngineSound, false);
        tankEngineSound.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                tankEngineSound.stop();
            }
        });
        if (tankEngineSound.getStatus().equals(MediaPlayer.Status.STOPPED)) {
            tankEngineSound.play();
        }

    }

    public char getInputForTankSteering() {
        return inputForTankSteering;
    }

    public PlayerController getPc() {
        return pc;
    }



}
