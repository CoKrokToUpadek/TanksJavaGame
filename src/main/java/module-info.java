module com.example.tanksjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports com.example.tanksjava.mainmenuwindow;
    exports com.example.tanksjava.gamewindow;
    opens com.example.tanksjava.gamewindow to javafx.fxml;
    opens com.example.tanksjava.mainmenuwindow to javafx.fxml;
    exports com.example.tanksjava to javafx.graphics;
    exports com.example.tanksjava.gamewindow.gameobjects.game_objects;
    opens com.example.tanksjava.gamewindow.gameobjects.game_objects to javafx.fxml;
    exports com.example.tanksjava.gamewindow.hibox_controllers;
    opens com.example.tanksjava.gamewindow.hibox_controllers to javafx.fxml;
}