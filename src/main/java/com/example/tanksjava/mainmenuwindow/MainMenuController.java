package com.example.tanksjava.mainmenuwindow;

import com.example.tanksjava.HelloApplication;
import com.example.tanksjava.toolsmethods.tools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.EventObject;

public class MainMenuController {
    @FXML
    public BorderPane mainMenuPane;

    @FXML
    public Button mmExitButton;
    @FXML
    public Button mmNewGameButton;


    private static final int gameWindowWidth=800;
    private static final int gameWindowHeight=600;

    private final String backgroundUrlString="com/example/tanksjava/mainMenuResources/image_processing20200410-24632-1tsak1g.png";

    @FXML
    private void initialize(){
        tools.setBackGround(mainMenuPane,backgroundUrlString,800,600);

    }

   /* public  void setBackGround(Pane pane){
        BackgroundImage myBI= new BackgroundImage(new Image("com/example/tanksjava/mainMenuResources/image_processing20200410-24632-1tsak1g.png",800,600,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
    }*/

    @FXML
    private void exitFromMainMenu(){
        System.exit(0);
    }

    @FXML
    private void moveToNewGameWindow(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        Stage newGameScene =(Stage) mmNewGameButton.getScene().getWindow();
        newGameScene.setScene(new Scene(fxmlLoader.load(), gameWindowWidth,gameWindowHeight));
        newGameScene.show();

    }

    public static int getGameWindowWidth() {
        return gameWindowWidth;
    }

    public static int getGameWindowHeight() {
        return gameWindowHeight;
    }

}