package controller;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        JeuController jeuController = new JeuController();
        stage.setTitle("Jeu");
        Scene scene = new Scene(jeuController.getBorderPane(),700,400);
        stage.setScene(scene);
        stage.show();
    }

}
