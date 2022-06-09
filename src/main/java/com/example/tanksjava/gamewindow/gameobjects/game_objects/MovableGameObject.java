package com.example.tanksjava.gamewindow.gameobjects.game_objects;

import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;
import com.example.tanksjava.gamewindow.assets.URLStringsOfAssets;
import com.example.tanksjava.gamewindow.gameobjects.muzzle_flash.MuzzleFlash;
import com.example.tanksjava.gamewindow.gameobjects.muzzle_flash.MuzzleFlashFrames;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MovableGameObject extends StaticGameObject {

    private final ObjectDirectionController playerDirectionController;
    private char inputForTankSteering;

    private final MediaPlayer tankEngineSound;

    private final MediaPlayer gunFireSound;
    //test
    private char previousInput;

    private boolean readyToFire = true;

    private MuzzleFlash muzzleFlash = new MuzzleFlash(0, URLStringsOfAssets.playerMuzzleFlashGraphicAsset3, 21, 38, false);

    private MuzzleFlashFrames muzzleFlashFrames = new MuzzleFlashFrames();

    private final int objectSpeed;


    //for tanks
    public MovableGameObject(int objectFlag, String objectURLString, int pixelSizeX, int pixelSizeY, int objectStartingPositionX,
                             int objectStartingPositionY, boolean isDestructible, int initialRotation, HitBoxController hitBoxController, int objectSpeed) {

        super(objectFlag, objectURLString, pixelSizeX, pixelSizeY, objectStartingPositionX, objectStartingPositionY, isDestructible, initialRotation, hitBoxController);
        this.objectSpeed = objectSpeed;
        playerDirectionController = new ObjectDirectionController(objectStartingPositionX, objectStartingPositionY, pixelSizeX, pixelSizeY, objectSpeed, initialRotation, hitBoxController);

        tankEngineSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.tankSoundMusicAsset).toUri().toString()));
        gunFireSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.gunFireSoundMusicAsset).toUri().toString()));
        //for initial settings
        playerDirectionController.updateBarrelPositionForVehicles();

        //flag set object
    }


    //for shells
    public MovableGameObject(int controltype, int objectFlag, String objectURLString, int pixelSizeX, int pixelSizeY, int objectStartingPositionX,
                             int objectStartingPositionY, boolean isDestructible, int initialRotation, HitBoxController hitBoxController, int objectSpeed) {
        super(objectFlag, objectURLString, pixelSizeX, pixelSizeY, objectStartingPositionX, objectStartingPositionY, isDestructible, initialRotation, hitBoxController);
        this.objectSpeed = objectSpeed;
        playerDirectionController = null;
        this.tankEngineSound=null;
        this.gunFireSound=null;
    }





    public void objectMovementInitialization(Pane pane) {

        insertObjectOnToPane(pane);

        //sneaky way to make it happen
        pane.getChildren().add(muzzleFlash.getObjectGraphics());
        muzzleFlash.getObjectGraphics().setLayoutX(0);
        muzzleFlash.getObjectGraphics().setLayoutY(0);
        muzzleFlash.getObjectGraphics().setVisible(false);


        pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                inputForTankSteering = event.getCharacter().charAt(0);
            }
        });
    }


    public void objectPositionAndOrientationUpdater() {

        if (inputForTankSteering == 'w' || inputForTankSteering == 's' || inputForTankSteering == 'a' || inputForTankSteering == 'd' || inputForTankSteering == 'r') {
            StaticToolsAndHandlers.updatePlayerHitBox(this, super.getHitBoxController());
            switch (inputForTankSteering){
                case 'w':
                    playerDirectionController.setObjectRotation(180);
                    break;
                case 's':
                    playerDirectionController.setObjectRotation(0);
                    break;
                case 'a':
                    playerDirectionController.setObjectRotation(90);
                    break;
                case 'd':
                    playerDirectionController.setObjectRotation(270);
                    break;
            }
            if (inputForTankSteering != 'r') {
                EngineSoundHandler();
                playerDirectionController.updateObjectPosition();
                playerDirectionController.updateBarrelPositionForVehicles();
                this.getObjectGraphics().setRotate(playerDirectionController.getObjectRotation());
                this.getObjectGraphics().setLayoutY(playerDirectionController.getCurrentPositionY());
                this.getObjectGraphics().setLayoutX(playerDirectionController.getCurrentPositionX());
                previousInput = inputForTankSteering;
            }
            if (inputForTankSteering == 'r') {
                if (readyToFire) {
//                  StaticToolsAndHandlers.playerMuzzleFlashHandler(this,8);
                    StaticToolsAndHandlers.playerMuzzleFlashHandler2(this);
                    gunFireSoundHandler();
                }
            }
            StaticToolsAndHandlers.updatePlayerHitBox(this, super.getHitBoxController());
            this.inputForTankSteering = 'x';
        }
    }

    public void EngineSoundHandler() {

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

    public void gunFireSoundHandler() {

        StaticToolsAndHandlers.musicAndSoundHandler(gunFireSound, false);
        gunFireSound.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                gunFireSound.stop();
            }
        });
        if (gunFireSound.getStatus().equals(MediaPlayer.Status.STOPPED)) {
            gunFireSound.play();
        }
    }

    public ObjectDirectionController getPlayerDirectionController() {
        return playerDirectionController;
    }

    public MuzzleFlash getMuzzleFlash() {
        return muzzleFlash;
    }


    public MuzzleFlashFrames getMuzzleFlashFrames() {
        return muzzleFlashFrames;
    }

    public boolean isReadyToFire() {
        return readyToFire;
    }

    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }
}
