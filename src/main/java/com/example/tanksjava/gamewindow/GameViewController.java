package com.example.tanksjava.gamewindow;

import com.example.tanksjava.toolsmethods.tools;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;


public class GameViewController {
    @FXML
    public AnchorPane newGamePane;

    @FXML
    public ImageView playerTank;

    private int currentRotation=180;





   private int playerStartingPosX =448;
   private int playerStartingPosY =488;

    private final String gameBoardURLString="com/example/tanksjava/gameWindowResources/tankGameMap.png";

    Image tankImage = new Image("com/example/tanksjava/gameWindowResources/tank_bigRed.png");
    @FXML
    private void initialize(){
        newGamePane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        movingPlayerTank();
        tools.setBackGround(newGamePane,gameBoardURLString,800,800);
    }


    public void movingPlayerTank(){


        Button button=new Button();
        newGamePane.getChildren().add(button);

        playerTank=new ImageView(tankImage);
        newGamePane.getChildren().add(playerTank);
        playerTank.setLayoutX(playerStartingPosX);
        playerTank.setLayoutY(playerStartingPosY);
        playerTank.setRotate(currentRotation);
        PlayerController  pc=new PlayerController(playerStartingPosX, playerStartingPosY);

           newGamePane.setOnKeyTyped(new EventHandler<KeyEvent>() {
               @Override
               public void handle(KeyEvent event) {
                   char input=event.getCharacter().charAt(0);
                   pc.updatePlayerCurrentPosition(input);
                   pc.playerRotation(input);
                   playerTank.setRotate(pc.getPlayerRotation());
                   playerTank.setLayoutY(pc.getCurrentPositionY());
                   playerTank.setLayoutX(pc.getCurrentPositionX());
                   System.out.println(pc.getCurrentPositionX());
                   System.out.println(pc.getCurrentPositionY());
                   System.out.println(playerTank.getRotate());
               }
           });
    }

}
