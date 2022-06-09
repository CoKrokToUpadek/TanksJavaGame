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


    private final MediaPlayer gunFireSound;

    //test
    private char previousInput;

    private boolean readyToFire=true;

    private MuzzleFlash muzzleFlash= new MuzzleFlash(0,URLStringsOfAssets.playerMuzzleFlashGraphicAsset3,21,38,false);

    private MuzzleFlashFrames muzzleFlashFrames=new MuzzleFlashFrames();

    private final int playerSpeed;


    public PlayerControlledGameObject(int objectFlag, String objectURLString, int pixelSizeX, int pixelSizeY, int objectStartingPositionX,
                                      int objectStartingPositionY, boolean isDestructible, int initialRotation, HitBoxController hitBoxController,int playerSpeed){

        super(objectFlag, objectURLString, pixelSizeX, pixelSizeY, objectStartingPositionX, objectStartingPositionY, isDestructible, initialRotation, hitBoxController);
        this.playerSpeed=playerSpeed;
        pc = new PlayerController(objectStartingPositionX, objectStartingPositionY, pixelSizeX, pixelSizeY, playerSpeed,initialRotation,hitBoxController);

        tankEngineSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.tankSoundMusicAsset).toUri().toString()));
        gunFireSound=new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.gunFireSoundMusicAsset).toUri().toString()));

        //flag set object




    }


    public void playerMovementInitialization(Pane pane) {

        insertObjectOnToPane(pane);

        //sneaky way to make it happen
        pane.getChildren().add(muzzleFlash.getObjectGraphics());
        muzzleFlash.getObjectGraphics().setLayoutX(0);
        muzzleFlash.getObjectGraphics().setLayoutY(0);
        muzzleFlash.getObjectGraphics().setVisible(false);

        //ordering for future usage closer to zero goes up
        //default is 0
        //negatives seems to work also
        //muzzleFlash.getObjectGraphics().setViewOrder(0);





        pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                inputForTankSteering = event.getCharacter().charAt(0);
                //for testing purpose
                // tankPositionAndOrientationUpdater();
            }
        });
    }


    public void tankPositionAndOrientationUpdater() {

        if (inputForTankSteering == 'w' || inputForTankSteering == 's' || inputForTankSteering == 'a' || inputForTankSteering == 'd' || inputForTankSteering == 'r') {
            StaticToolsAndHandlers.updatePlayerHitBox(0, this, super.getHitBoxController());

            if(inputForTankSteering!='r'){
                playerEngineSoundHandler();
            }

            pc.updatePlayerCurrentPosition(inputForTankSteering);
            pc.playerRotation(inputForTankSteering);
            pc.updatePlayerBarrelPosition();

            this.getObjectGraphics().setRotate(pc.getPlayerRotation());
            this.getObjectGraphics().setLayoutY(pc.getCurrentPositionY());
            this.getObjectGraphics().setLayoutX(pc.getCurrentPositionX());
            previousInput=inputForTankSteering;


            if (inputForTankSteering=='r'){
                if(readyToFire){
//                   StaticToolsAndHandlers.playerMuzzleFlashHandler(this,8);
                    StaticToolsAndHandlers.playerMuzzleFlashHandler2(this);
                    gunFireSoundHandler();
                }
            }


            StaticToolsAndHandlers.updatePlayerHitBox(this.getObjectFlag(), this, super.getHitBoxController());
                      this.inputForTankSteering = 'x';
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

    public PlayerController getPc() {
        return pc;
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
