module com.example.tanksjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.example.tanksjava to javafx.fxml;
    exports com.example.tanksjava;
    exports com.example.tanksjava.mainmenuwindow;
    opens com.example.tanksjava.mainmenuwindow to javafx.fxml;
}