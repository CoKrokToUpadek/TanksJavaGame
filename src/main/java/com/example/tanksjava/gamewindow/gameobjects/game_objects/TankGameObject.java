package com.example.tanksjava.gamewindow.gameobjects.game_objects;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.shells.ShellGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.shells.ShellObjectList;
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

public class TankGameObject extends StaticGameObject {

    private final ObjectDirectionController tankDirectionController;

    private final ShellObjectList tankShells;

    private final ShellGameObject tankShell;

    private char inputForTankSteering;

    private final MediaPlayer tankEngineSound;

    private final MediaPlayer gunFireSound;
    //test

    private boolean readyToFire = true;

    private final MuzzleFlash muzzleFlash = new MuzzleFlash(0, URLStringsOfAssets.playerMuzzleFlashGraphicAsset3, 21, 38, false);

    private final MuzzleFlashFrames muzzleFlashFrames = new MuzzleFlashFrames();

    private final boolean isPlayerControlled;

    private boolean collisionInPreviousMove;


    //for tanks
    public TankGameObject(boolean isPlayerControlled, int objectFlag, String objectURLString, int pixelSizeX, int pixelSizeY, int objectStartingPositionX,
                          int objectStartingPositionY, boolean isDestructible, int initialRotation, HitBoxController hitBoxController, int objectSpeed, ShellGameObject playerShell) {

        super(objectFlag, objectURLString, pixelSizeX, pixelSizeY, objectStartingPositionX, objectStartingPositionY, isDestructible, initialRotation, hitBoxController);
        tankDirectionController = new ObjectDirectionController(objectStartingPositionX, objectStartingPositionY, pixelSizeX, pixelSizeY, objectSpeed, initialRotation, hitBoxController);

        tankEngineSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.tankSoundMusicAsset).toUri().toString()));
        gunFireSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.gunFireSoundMusicAsset).toUri().toString()));
        //for initial settings
        tankDirectionController.updateBarrelPositionForVehicles();
        this.tankShell = playerShell;
        tankShells = new ShellObjectList();
        this.isPlayerControlled = isPlayerControlled;

        //for AI
        collisionInPreviousMove = false;
        if (!isPlayerControlled) {
            this.inputForTankSteering = 's';
        }

    }

    public TankGameObject(TankGameObject tank){
        super(tank.getObjectFlag().getFlagValue(), tank.getObjectURLString(), tank.getObjectSizeX(), tank.getObjectSizeY(), tank.getObjectStartingPositionX(), tank.getObjectStartingPositionY(), tank.isDestructible(), tank.getInitialRotation(),tank.getHitBoxController());

        tankDirectionController = new ObjectDirectionController(tank.getObjectStartingPositionX(), tank.getObjectStartingPositionY(), tank.getObjectSizeX(),tank.getObjectSizeY(),tank.tankDirectionController.getObjectSpeed(),tank.getInitialRotation(),tank.getHitBoxController());
        tankEngineSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.tankSoundMusicAsset).toUri().toString()));
        gunFireSound = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.gunFireSoundMusicAsset).toUri().toString()));
        //for initial settings
        tankDirectionController.updateBarrelPositionForVehicles();
        this.tankShell = tank.tankShell;
        tankShells = new ShellObjectList();
        this.isPlayerControlled = tank.isPlayerControlled;

        //for AI
        collisionInPreviousMove = false;
        if (!isPlayerControlled) {
            this.inputForTankSteering = 's';
        }

    }




    public void tankMovementInitialization(Pane pane) {

        insertObjectOnToPane(pane);

        //sneaky way to make it happen
        pane.getChildren().add(muzzleFlash.getObjectGraphics());
        muzzleFlash.getObjectGraphics().setLayoutX(0);
        muzzleFlash.getObjectGraphics().setLayoutY(0);
        muzzleFlash.getObjectGraphics().setVisible(false);


        if (isPlayerControlled) {
            pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    inputForTankSteering = event.getCharacter().charAt(0);
                }
            });
        }

    }


    public void tankPositionAndOrientationUpdater(long randomNumber)  {
        if (!getTankDirectionController().getOwnerGotHit()){
            char temp;
            if (isPlayerControlled) {
                if (inputForTankSteering == 'w' || inputForTankSteering == 's' || inputForTankSteering == 'a' || inputForTankSteering == 'd' || inputForTankSteering == 'r') {
                    rotationHandler(inputForTankSteering);
                    if (inputForTankSteering != 'r') {
                        tankEngineSoundHandler();
                        positionUpdateHandler();
                    }
                    if (inputForTankSteering == 'r') {
                        fireTheGunHandler(readyToFire);
                    }
                    this.inputForTankSteering = 'x';
                }

            } else {
                if (randomNumber % 10 == 0) {
                    if (collisionInPreviousMove) {
                        collisionInPreviousMove = false;
                        temp = inputForTankSteering;
                        inputForTankSteering = StaticToolsAndHandlers.collisionOutPutHandler(temp);
                        positionUpdateHandler();
                    } else {
                        positionUpdateHandler();
                    }
                }
                if (randomNumber % 200 == 0) {
                    fireTheGunHandler(readyToFire);
                    positionUpdateHandler();

                }

            }
            StaticToolsAndHandlers.updateObjectHitBox(this, super.getHitBoxController());
        }

    }


    private void positionUpdateHandler() {
        rotationHandler(inputForTankSteering);
        collisionInPreviousMove = tankDirectionController.updateTankPositionV2();
        tankDirectionController.updateBarrelPositionForVehicles();
        this.getObjectGraphics().setRotate(tankDirectionController.getObjectRotation());
        this.getObjectGraphics().setLayoutY(tankDirectionController.getCurrentPositionY());
        this.getObjectGraphics().setLayoutX(tankDirectionController.getCurrentPositionX());
    }

    private void rotationHandler(char inputForTankSteering) {
        switch (inputForTankSteering) {
            case 'w':
                tankDirectionController.setObjectRotation(180);
                break;
            case 's':
                tankDirectionController.setObjectRotation(0);
                break;
            case 'a':
                tankDirectionController.setObjectRotation(90);
                break;
            case 'd':
                tankDirectionController.setObjectRotation(270);
                break;
        }

    }

    private void fireTheGunHandler(boolean isReady) {
        if (isReady) {
            StaticToolsAndHandlers.playerMuzzleFlashHandler(this,8);
            tankShells.addNewShellToList(new ShellGameObject(tankShell, this));

//            System.out.println("--------------------------------------------------------------------------");
//            System.out.println("barrelX=" + this.getTankDirectionController().getCurrentBarrelPositionX());
//            System.out.println("barrelY=" + this.getTankDirectionController().getCurrentBarrelPositionY());
//            System.out.println("--------------------------------------------------------------------------");
//            System.out.println();
            //StaticToolsAndHandlers.playerMuzzleFlashHandler(this,8);
            tankGunFireSoundHandler();
        }
    }

    public void tankEngineSoundHandler() {

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

    public void tankGunFireSoundHandler() {

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

    public ObjectDirectionController getTankDirectionController() {
        return tankDirectionController;
    }

    public MuzzleFlash getMuzzleFlash() {
        return muzzleFlash;
    }


    public MuzzleFlashFrames getMuzzleFlashFrames() {
        return muzzleFlashFrames;
    }

    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }

    public ShellObjectList getTankShells() {
        return tankShells;
    }






}
