module com.example.tp7_mvc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens view to javafx.fxml;
    exports view;
    exports controller;
    opens controller to javafx.fxml;

    exports model;
    opens model to javafx.fxml;

    requires gson ;




//    opens com.example.tp7_mvc to javafx.fxml;
//    exports com.example.tp7_mvc;
}