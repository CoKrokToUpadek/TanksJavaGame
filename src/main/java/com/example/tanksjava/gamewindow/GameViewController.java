package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.game_objects.TankGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.shells.ShellGameObject;
import com.example.tanksjava.gamewindow.gameobjects.game_objects.StaticGameObject;
import com.example.tanksjava.gamewindow.assets.URLStringsOfAssets;
import com.example.tanksjava.gamewindow.hibox_controllers.HitBoxController;
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

    private final int playerStartingPosX = 600;
    private final int playerStartingPosY = 500;


    private final int enemyStartingPosX = 40;
    private final int enemyStartingPosY = 40;

    private final int gameBoardSizeX = 800;
    private final int gameBoardSizeY = 600;

    HitBoxController hitBoxController = new HitBoxController(gameBoardSizeX, gameBoardSizeY);



    private final StaticGameObject metalCrate = new StaticGameObject(1, URLStringsOfAssets.metalBoxGraphicAsset, 28, 28, 80,
            80, false, 0,hitBoxController);
    private final StaticGameObject woodenCrate = new StaticGameObject(1, URLStringsOfAssets.woodenBoxGraphicAsset, 28, 28, 80,
            80, true, 0, hitBoxController);

    private final ShellGameObject playerShell=new ShellGameObject(3,URLStringsOfAssets.playerShellGraphicAsset,8,18,true,6,hitBoxController);

    private final ShellGameObject enemyShell=new ShellGameObject(3,URLStringsOfAssets.enemyShellGraphicAsset,8,18,true,6,hitBoxController);

    TankGameObject player1Tank;

    TankGameObject enemy1Tank;



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


        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 21, 0, 6, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 21, 772, 6, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 6, 100, 180, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);

        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 26, 36, 0, StaticToolsAndHandlers.itemOrientation.HORIZONTAL, hitBoxController);
        StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, metalCrate, 26, 36, 572, StaticToolsAndHandlers.itemOrientation.HORIZONTAL, hitBoxController);

        for (int i=496;i<700;i+=28){
            StaticToolsAndHandlers.addStaticObjectsInBulk(newGamePane, new StaticGameObject(woodenCrate), 6, i, 180, StaticToolsAndHandlers.itemOrientation.VERTICAL, hitBoxController);

        }



        player1Tank = new TankGameObject(true,2,URLStringsOfAssets.playerSingleBarrelTankGraphicAsset, 52, 52,
                playerStartingPosX, playerStartingPosY, true, 180, hitBoxController,4, playerShell);

        player1Tank.tankMovementInitialization(newGamePane);


        enemy1Tank=new TankGameObject(false, 4,URLStringsOfAssets.enemySingleBarrelTankGraphicAsset, 46, 46,
                enemyStartingPosX, enemyStartingPosY, true, 0, hitBoxController,4, enemyShell);

        enemy1Tank.tankMovementInitialization(newGamePane);



    }


    public void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                StaticToolsAndHandlers.addFrames();


                player1Tank.tankPositionAndOrientationUpdater(0);
                if (!player1Tank.getTankShells().getShellList().isEmpty()){
                    player1Tank.getTankShells().shellListPositionUpdate(newGamePane);
                }
                enemy1Tank.tankPositionAndOrientationUpdater(StaticToolsAndHandlers.getFramesCounter());
                if (!enemy1Tank.getTankShells().getShellList().isEmpty()){
                    enemy1Tank.getTankShells().shellListPositionUpdate(newGamePane);
                }

            }
        };
        gameLoop.start();
    }

}
