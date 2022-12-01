package view;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JeuUI  {

    GridPane gridPane = new GridPane();


    public VBox createUserInfoLeft(){
        VBox vBox = new VBox(10);


        Label infoLabel = new Label("Information");
        Label firstNameLabel = new Label("Nom : Mahdi");
        Label lastNameLabel  = new Label("Prenom :");
        Label scoreLabel     = new Label("Score : ");
        Label colorLabel     = new Label("Comflowerblue");
        colorLabel.setTextFill(Color.web("#0101fe"));

        vBox.getChildren().addAll(infoLabel, firstNameLabel, lastNameLabel, scoreLabel, colorLabel);
        return vBox;
    }
    public VBox createJeu(){

        VBox vBox = new VBox();

        gridPane.add(button50, 0, 0);
        gridPane.add(button51, 1, 0);
        gridPane.add(button52, 2, 0);
        gridPane.add(button53, 3, 0);
        gridPane.add(button54, 4, 0);
        gridPane.add(button55, 5, 0);
        gridPane.add(button56, 6, 0);


        gridPane.add(button40, 0, 1);
        gridPane.add(button41, 1, 1);
        gridPane.add(button42, 2, 1);
        gridPane.add(button43, 3, 1);
        gridPane.add(button44, 4, 1);
        gridPane.add(button45, 5, 1);
        gridPane.add(button46, 6, 1);

        gridPane.add(button30, 0, 2);
        gridPane.add(button31, 1, 2);
        gridPane.add(button32, 2, 2);
        gridPane.add(button33, 3, 2);
        gridPane.add(button34, 4, 2);
        gridPane.add(button35, 5, 2);
        gridPane.add(button36, 6, 2);

        gridPane.add(button20, 0, 3);
        gridPane.add(button21, 1, 3);
        gridPane.add(button22, 2, 3);
        gridPane.add(button23, 3, 3);
        gridPane.add(button24, 4, 3);
        gridPane.add(button25, 5, 3);
        gridPane.add(button26, 6, 3);

        gridPane.add(button10, 0, 4);
        gridPane.add(button11, 1, 4);
        gridPane.add(button12, 2, 4);
        gridPane.add(button13, 3, 4);
        gridPane.add(button14, 4, 4);
        gridPane.add(button15, 5, 4);
        gridPane.add(button16, 6, 4);

        gridPane.add(button00, 0, 5);
        gridPane.add(button01, 1, 5);
        gridPane.add(button02, 2, 5);
        gridPane.add(button03, 3, 5);
        gridPane.add(button04, 4, 5);
        gridPane.add(button05, 5, 5);
        gridPane.add(button06, 6, 5);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        vBox.getChildren().addAll(gridPane);

        return vBox;
    }



    Button button50 = new Button("5 0");
    Button button51 = new Button("5 1");
    Button button52 = new Button("5 2");
    Button button53 = new Button("5 3");
    Button button54 = new Button("5 4");
    Button button55 = new Button("5 5");
    Button button56 = new Button("5 6");


    Button button40 = new Button("4 0");
    Button button41 = new Button("4 1");
    Button button42 = new Button("4 2");
    Button button43 = new Button("4 3");
    Button button44 = new Button("4 4");
    Button button45 = new Button("4 5");
    Button button46 = new Button("4 6");


    Button button30 = new Button("3 0");
    Button button31 = new Button("3 1");
    Button button32 = new Button("3 2");
    Button button33 = new Button("3 3");
    Button button34 = new Button("3 4");
    Button button35 = new Button("3 5");
    Button button36 = new Button("3 6");

    Button button20 = new Button("2 0");
    Button button21 = new Button("2 1");
    Button button22 = new Button("2 2");
    Button button23 = new Button("2 3");
    Button button24 = new Button("2 4");
    Button button25 = new Button("2 5");
    Button button26 = new Button("2 6");


    Button button10 = new Button("1 0");
    Button button11 = new Button("1 1");
    Button button12 = new Button("1 2");
    Button button13 = new Button("1 3");
    Button button14 = new Button("1 4");
    Button button15 = new Button("1 5");
    Button button16 = new Button("1 6");


    Button button00 = new Button("0 0");
    Button button01 = new Button("0 1");
    Button button02 = new Button("0 2");
    Button button03 = new Button("0 3");
    Button button04 = new Button("0 4");
    Button button05 = new Button("0 5");
    Button button06 = new Button("0 6");

}
