package com.example.tanksjava.toolsmethods;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class tools {

    public static void setBackGround(Pane pane, String url, int sizeX, int sizeY){
        BackgroundImage myBI= new BackgroundImage(new Image(url,sizeX,sizeY,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
    }

}
