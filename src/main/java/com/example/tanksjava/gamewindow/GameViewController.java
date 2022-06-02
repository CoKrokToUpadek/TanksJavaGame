package com.example.tanksjava.gamewindow;

import com.example.tanksjava.gamewindow.gameobjects.StaticGameObject;
import com.example.tanksjava.toolsmethods.tools;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.nio.file.Paths;


public class GameViewController {
    @FXML
    public AnchorPane newGamePane;
    @FXML
    public ImageView playerTank;

    private int currentRotation=180;

    private MediaPlayer backgroundMusic;
    private MediaPlayer tanksoundMusic;

    public AnimationTimer gameLoop;


   private int playerStartingPosX =448;
   private int playerStartingPosY =488;

   private StaticGameObject crate=new StaticGameObject("com/example/tanksjava/gameWindowResources/crateMetal.png",28,28,80,80,false);
//test
    PlayerController  pc=new PlayerController(playerStartingPosX, playerStartingPosY);
char input;

    private final String gameBoardURLString="com/example/tanksjava/gameWindowResources/tankGameMap.png";
    private final String backGroundMusicURLString= "src/main/resources/com/example/tanksjava/gameWindowResources/music.mp3";
    private final String tankSoundULRString="src/main/resources/com/example/tanksjava/gameWindowResources/loop_2.wav";

    Image tankImage = new Image("com/example/tanksjava/gameWindowResources/tank_bigRed.png");
    @FXML
    private void initialize(){
        newGamePane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        playerMovementInitialization();
        tools.setBackGround(newGamePane,gameBoardURLString,800,800);

        backgroundMusic = new MediaPlayer(new Media(Paths.get(backGroundMusicURLString).toUri().toString()));
        tanksoundMusic = new MediaPlayer(new Media(Paths.get(tankSoundULRString).toUri().toString()));

        backGroundMusic(backgroundMusic,true);
        startGameLoop();
        crate.insertObjectOnToPane(newGamePane);

    }


    public void playerMovementInitialization(){


        Button button=new Button();
        button.setVisible(true);
        newGamePane.getChildren().add(button);

        playerTank=new ImageView(tankImage);
        newGamePane.getChildren().add(playerTank);
        playerTank.setLayoutX(playerStartingPosX);
        playerTank.setLayoutY(playerStartingPosY);
        playerTank.setRotate(currentRotation);


           newGamePane.setOnKeyTyped(new EventHandler<KeyEvent>() {
               @Override
               public void handle(KeyEvent event) {

                   tankEngineSoundHandler();
                   input=event.getCharacter().charAt(0);
               }
           });
    }

    public void backGroundMusic(MediaPlayer controller, boolean isLooped){
        if(!tanksoundMusic.getStatus().equals(MediaPlayer.Status.PLAYING)){
            if (isLooped){
                controller.setCycleCount(MediaPlayer.INDEFINITE);
            }
            controller.setVolume(0.05);
            controller.play();
        }
    }

    public void tankEngineSoundHandler(){
        backGroundMusic(tanksoundMusic ,false);
        tanksoundMusic.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                tanksoundMusic.stop();
            }
        });
        if (tanksoundMusic.getStatus().equals(MediaPlayer.Status.STOPPED)){
            tanksoundMusic.play();
        }

        System.out.println(tanksoundMusic.getStatus());
    }

    public void tankPositionAndOrientationUpdater(PlayerController pc,char input){

        pc.updatePlayerCurrentPosition(input);
        pc.playerRotation(input);
        playerTank.setRotate(pc.getPlayerRotation());
        playerTank.setLayoutY(pc.getCurrentPositionY());
        playerTank.setLayoutX(pc.getCurrentPositionX());
        System.out.println(pc.getCurrentPositionX());
        System.out.println(pc.getCurrentPositionY());
        System.out.println(playerTank.getRotate());
        //clear input for the next frame
        this.input='x';
    }

    public void startGameLoop(){
        gameLoop =new AnimationTimer() {
            @Override
            public void handle(long now) {
                tankPositionAndOrientationUpdater(pc,input);
            }
        };
        gameLoop.start();
    }

}
