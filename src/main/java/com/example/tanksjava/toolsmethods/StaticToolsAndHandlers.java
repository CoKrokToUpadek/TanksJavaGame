package com.example.tanksjava.toolsmethods;

import com.example.tanksjava.gamewindow.hibox_controllers.Flag;
import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.MovableGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.StaticGameObject;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;

import java.util.concurrent.atomic.AtomicInteger;


public class StaticToolsAndHandlers {




    public enum itemOrientation {
        HORIZONTAL, VERTICAL
    }
    private static Rotate rotate=new Rotate();

    private static int objectCounter=0;

    public static int getObjectNumber(){
        objectCounter++;
        return objectCounter;
    }

    public static void setBackGround(Pane pane, String url, int sizeX, int sizeY) {
        BackgroundImage myBI = new BackgroundImage(new Image(url, sizeX, sizeY, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
    }


    public static void musicAndSoundHandler(MediaPlayer mediaPlayer, boolean isLooped) {
        if (!mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            if (isLooped) {
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
            mediaPlayer.setVolume(0.05);
            mediaPlayer.play();
        }
    }

    //TODO needs to be fixed-rotate functionality is currently commented out
    public static void playerMuzzleFlashHandler2(MovableGameObject playerObject) {


        int tempPositionX = playerObject.getPlayerDirectionController().getCurrentBarrelPositionX();
        int tempPositionY = playerObject.getPlayerDirectionController().getCurrentBarrelPositionY();
        int tempRotation = playerObject.getPlayerDirectionController().getObjectRotation();
        AtomicInteger tempFlashSizeX = new AtomicInteger();
        AtomicInteger tempFlashSizeY = new AtomicInteger();


        Thread timerThread = new Thread(() -> {
            playerObject.setReadyToFire(false);
            for (int i = 0; i < 3; i++) {
                tempFlashSizeX.set(playerObject.getMuzzleFlashFrames().getMuzzleFlashStages().get(i).getObjectSizeX());
                tempFlashSizeY.set(playerObject.getMuzzleFlashFrames().getMuzzleFlashStages().get(i).getObjectSizeY());
                playerObject.getMuzzleFlash().getObjectGraphics().setLayoutX(tempPositionX - (tempFlashSizeX.get() / 2));
                playerObject.getMuzzleFlash().getObjectGraphics().setLayoutY(tempPositionY);
                playerObject.getMuzzleFlash().setImageGraphic(playerObject.getMuzzleFlashFrames().getMuzzleFlashStages().get(i).getObjectURLString());

//                  rotate.setPivotX(tempPositionX);
//                  rotate.setPivotY(tempPositionY);
//                    rotate.setAngle(270);
//                    rotate.setPivotX(tempPositionX);
//                    rotate.setPivotY(tempPositionY);
//                    playerObject.getMuzzleFlash().getObjectGraphics().getTransforms().add(rotate);

                try {
                    switch (tempRotation) {
                        case 0://s

                            break;
                        case 180://w

                            break;
                        case 270://d
//                            if (i == 0) {
//                                playerObject.getMuzzleFlash().getObjectGraphics().getTransforms();
//                            }
//
//                            System.out.println(playerObject.getMuzzleFlash().getObjectGraphics().getTransforms());
//                            playerObject.getMuzzleFlash().getObjectGraphics().setRotate(270);


                            break;
                        case 90://a

                            break;
                    }
                      playerObject.getMuzzleFlash().getObjectGraphics().setVisible(true);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                      playerObject.getMuzzleFlash().getObjectGraphics().setVisible(false);


            }
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            playerObject.setReadyToFire(true);

            //test
//             playerObject.getMuzzleFlash().getObjectGraphics().getTransforms().clear();
        });
        timerThread.start();

    }

    public static void playerMuzzleFlashHandler(MovableGameObject playerObject, int pivotCorrection) {


        int tempPositionX = playerObject.getPlayerDirectionController().getCurrentBarrelPositionX();
        int tempPositionY = playerObject.getPlayerDirectionController().getCurrentBarrelPositionY();
        int tempRotation = playerObject.getPlayerDirectionController().getObjectRotation();
        AtomicInteger tempFlashSizeX = new AtomicInteger();
        AtomicInteger tempFlashSizeY = new AtomicInteger();

        playerObject.getMuzzleFlash().getObjectGraphics().setRotate(tempRotation);


        Thread timerThread = new Thread(() -> {
            playerObject.setReadyToFire(false);
            for (int i = 0; i < 3; i++) {
                playerObject.getMuzzleFlash().setImageGraphic(playerObject.getMuzzleFlashFrames().getMuzzleFlashStages().get(i).getObjectURLString());
                tempFlashSizeX.set(playerObject.getMuzzleFlashFrames().getMuzzleFlashStages().get(i).getObjectSizeX());
                tempFlashSizeY.set(playerObject.getMuzzleFlashFrames().getMuzzleFlashStages().get(i).getObjectSizeY());

                try {
                    switch (tempRotation) {
                        case 0://s
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutX(tempPositionX - (tempFlashSizeX.get() / 2));
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutY(tempPositionY);
                            break;
                        case 180://w
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutX(tempPositionX - (tempFlashSizeX.get() / 2));
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutY(tempPositionY - tempFlashSizeY.get());
                            break;
                        case 270://d
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutX(tempPositionX + pivotCorrection - 3 + i);
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutY(tempPositionY - tempFlashSizeX.get() + 3 - i);
                            break;
                        case 90://a
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutX(tempPositionX - tempFlashSizeX.get() - pivotCorrection + 3 - i);
                            playerObject.getMuzzleFlash().getObjectGraphics().setLayoutY(tempPositionY - tempFlashSizeX.get() + 3 - i);
                            break;
                    }
                    Platform.runLater(() -> {
                        playerObject.getMuzzleFlash().getObjectGraphics().setVisible(true);
                    });

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    playerObject.getMuzzleFlash().getObjectGraphics().setVisible(false);
                });
            }


            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            playerObject.setReadyToFire(true);
        });
        timerThread.start();


    }


    public static void insertObjectOnToPane(Pane pane, StaticGameObject object, int objectStartingPositionX, int objectStartingPositionY, int initialRotation, HitBoxController hitBoxController) {
        pane.getChildren().add(object.getObjectGraphics());
        object.getObjectGraphics().setLayoutX(objectStartingPositionX);
        object.getObjectGraphics().setLayoutY(objectStartingPositionY);
        object.getObjectGraphics().setRotate(initialRotation);
        hitBoxController.fillHitBoxArrayWithFlags(object.getObjectFlag(), objectStartingPositionX, objectStartingPositionY, object.getObjectSizeX(), object.getObjectSizeY());

    }

    public static void updatePlayerHitBox(MovableGameObject object, HitBoxController hitBoxController) {
        hitBoxController.fillHitBoxArrayWithFlags(object.getObjectFlag(), object.getPlayerDirectionController().getCurrentPositionX(), object.getPlayerDirectionController().getCurrentPositionY(), object.getObjectSizeX(), object.getObjectSizeY());
    }

    public static void clearPlayerHitBox(MovableGameObject object, HitBoxController hitBoxController) {
        hitBoxController.fillHitBoxArrayWithFlags(new Flag(0), object.getPlayerDirectionController().getCurrentPositionX(), object.getPlayerDirectionController().getCurrentPositionY(), object.getObjectSizeX(), object.getObjectSizeY());
    }

    public static void clearStaticHitBox(StaticGameObject object, HitBoxController hitBoxController) {
        object.getObjectGraphics().setVisible(false);
        hitBoxController.fillHitBoxArrayWithFlags(new Flag(0), object.getObjectStartingPositionX(),object.getObjectStartingPositionY(), object.getObjectSizeX(), object.getObjectSizeY());
    }

    public static void staticObjectRemover(StaticGameObject object, HitBoxController hitBoxController){
        object.getObjectGraphics().setVisible(false);
        clearStaticHitBox(object, hitBoxController);


    }


    public static void addStaticObjectsInBulk(Pane pane, StaticGameObject object, int numberOfObjects, int startingPositionX, int startingPositionY, StaticToolsAndHandlers.itemOrientation orientation, HitBoxController hitBoxController) {

        int sizeX = 0;
        int sizeY = 0;


        for (int i = 0; i < numberOfObjects; i++) {
            if (orientation.equals(StaticToolsAndHandlers.itemOrientation.HORIZONTAL)) {
                StaticGameObject temp=new StaticGameObject(object);
                temp.setObjectStartingPositionX(startingPositionX + sizeX);
                temp.setObjectStartingPositionY(startingPositionY);
                StaticToolsAndHandlers.insertObjectOnToPane(pane, temp, startingPositionX + sizeX, startingPositionY, 0, hitBoxController);
                sizeX += object.getObjectSizeX();
            } else {
                StaticGameObject temp=new StaticGameObject(object);
                temp.setObjectStartingPositionX(startingPositionX);
                temp.setObjectStartingPositionY(startingPositionY+sizeY);
                StaticToolsAndHandlers.insertObjectOnToPane(pane, temp, startingPositionX, startingPositionY + sizeY, 0, hitBoxController);
                sizeY += object.getObjectSizeY();
            }

        }

    }


}
