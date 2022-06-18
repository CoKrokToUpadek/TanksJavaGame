package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.TankGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.TankGameObjectList;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.shells.ShellGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.StaticGameObject;
import com.example.tanksjava.gamewindow.assets.URLStringsOfAssets;
import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;

import java.nio.file.Paths;
import java.util.Optional;


public class GameViewController {
    @FXML
    public AnchorPane newGamePane;

    //must be global otherwise garbage collector removes it after 1 second
    private MediaPlayer backgroundMusic;

    public AnimationTimer gameLoop;


    //for easy access
    private final int playerStartingPosX = 600;
    private final int playerStartingPosY = 500;


    private final int enemyStartingPosX = 400;
    private final int enemyStartingPosY = 500;

    private final int gameBoardSizeX = 800;
    private final int gameBoardSizeY = 600;

    HitBoxController hitBoxController = new HitBoxController(gameBoardSizeX, gameBoardSizeY);


    private StaticGameObject metalCrate;
    private StaticGameObject woodenCrate;

    private ShellGameObject playerShell;

    private ShellGameObject enemyShell;

    private TankGameObject player1Tank;

    private TankGameObject enemy1Tank;


    private TankGameObjectList enemyTankList;


    @FXML
    private void initialize() {
        //background settings
        StaticToolsAndHandlers.setBackGround(newGamePane, URLStringsOfAssets.gameBoardGraphicAsset, gameBoardSizeX, gameBoardSizeY);
        backgroundMusic = new MediaPlayer(new Media(Paths.get(URLStringsOfAssets.backGroundMusicAsset).toUri().toString()));
        StaticToolsAndHandlers.musicAndSoundHandler(backgroundMusic, true);
        startGameLoop();

        //program doesn't work without it
        Button button = new Button();
        button.setVisible(true);
        newGamePane.getChildren().add(button);
        button.setScaleX(0.1);
        button.setScaleY(0.1);


        metalCrate = new StaticGameObject(1, URLStringsOfAssets.metalBoxGraphicAsset, 28, 28, 80,
                80, false, 0, hitBoxController);
        woodenCrate = new StaticGameObject(1, URLStringsOfAssets.woodenBoxGraphicAsset, 28, 28, 80,
                80, true, 0, hitBoxController);


        playerShell = new ShellGameObject(3, URLStringsOfAssets.playerShellGraphicAsset, 8, 18, true, 6, hitBoxController);
        player1Tank = new TankGameObject(true, 2, URLStringsOfAssets.playerSingleBarrelTankGraphicAsset, 52, 52,
                playerStartingPosX, playerStartingPosY, true, 180, hitBoxController, 4, playerShell);

        enemyShell = new ShellGameObject(4, URLStringsOfAssets.enemyShellGraphicAsset, 8, 18, true, 6, hitBoxController);
        enemy1Tank = new TankGameObject(false, 5, URLStringsOfAssets.enemySingleBarrelTankGraphicAsset, 46, 46,
                enemyStartingPosX, enemyStartingPosY, true, 0, hitBoxController, 4, enemyShell);



        player1Tank.tankMovementInitialization(newGamePane);
        enemyTankList=new TankGameObjectList();

        enemyTankList.addEnemyTanksInBulk(enemy1Tank,5,40,40,150);
        enemyTankList.initiateTanks(newGamePane);
        drawFirstGameLevel();




    }


    public void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                StaticToolsAndHandlers.addFrames();
                    player1Tank.tankPositionAndOrientationUpdater(0);
                    if (!player1Tank.getTankShells().getShellList().isEmpty()) {
                        player1Tank.getTankShells().shellListPositionUpdate(newGamePane);
                    }
                    enemyTankList.updateTanksPosition(StaticToolsAndHandlers.getFramesCounter(),newGamePane);

                    if (player1Tank.getTankDirectionController().getOwnerGotHit()){
                        gameLoop.stop();
                        Alert alert = youLostPopUp();

                        Platform.runLater(()->{
                            Optional<ButtonType> result =   alert.showAndWait();
                            if(result.get()==ButtonType.OK){
                                System.exit(0);
                            }
                        });

                    }
                if (enemyTankList.getTankList().isEmpty()){
                    gameLoop.stop();
                    Alert alert = youWonPopUp();

                    Platform.runLater(()->{
                        Optional<ButtonType> result =   alert.showAndWait();
                        if(result.get()==ButtonType.OK){
                            System.exit(0);
                        }
                    });
                }



            }
        };
        gameLoop.start();
    }

    private void insertMapBoundaries() {
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 21, 0, 6, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 21, 772, 6, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 26, 36, 0, StaticToolsAndHandlers.itemOrientation.HORIZONTAL, hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 26, 36, 572, StaticToolsAndHandlers.itemOrientation.HORIZONTAL, hitBoxController);

    }

    private void drawFirstGameLevel() {
        insertMapBoundaries();
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 15, 28, 405, StaticToolsAndHandlers.itemOrientation.HORIZONTAL, hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 6, 100, 180, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        for (int i = 496; i < 700; i += 28) {
            StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, new StaticGameObject(woodenCrate), 6, i, 180, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);

        }

        for (int i = 360; i < 430; i += 28) {
            StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, new StaticGameObject(woodenCrate), 2, i, 180, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        }
        for (int i = 360; i < 430; i += 28) {
            StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, new StaticGameObject(woodenCrate), 1, i, 310, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        }

        for (int i = 130; i < 230; i += 28) {
            StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, new StaticGameObject(woodenCrate), 6, i, 170, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        }

    }

    private Alert youWonPopUp(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("You won!");
        alert.setGraphic(null);
        alert.setHeaderText("");
        alert.setContentText("You Won, press ExitApp to Quit");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("ExitApp");
      return alert;
    }

    private Alert youLostPopUp(){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("You Lost!");
            alert.setGraphic(null);
            alert.setHeaderText("");
            alert.setContentText("You lost, press ExitApp to Quit");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("ExitApp");
            return alert;
    }

}
