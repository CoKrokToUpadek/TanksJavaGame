package com.example.tanksjava.mainmenuwindow;

import com.example.tanksjava.HelloApplication;
import com.example.tanksjava.toolsmethods.StaticToolsAndHandlers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

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
        StaticToolsAndHandlers.setBackGround(mainMenuPane,backgroundUrlString,800,600);

    }

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