package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Joueur;

public class JeuUI  {

   private GridPane gridPane = new GridPane();
   private Button[][] buttons = new Button[7][7];


    public VBox createUserInfoLeft(Joueur joueur){
        VBox vBox = new VBox(10);


        Label infoLabel = new Label("Information");
        Label firstNameLabel = new Label("Nom : "+ joueur.getFirstName());
        Label lastNameLabel  = new Label("Prenom : "+ joueur.getLastName());
        Label scoreLabel     = new Label("Score : "+ joueur.getScore());
        Label colorLabel     = new Label("Comflowerblue");
        colorLabel.setTextFill(Color.web("#0101fe"));

        vBox.getChildren().addAll(infoLabel, firstNameLabel, lastNameLabel, scoreLabel, colorLabel);
        return vBox;
    }
    public VBox createUserInfoRight(Joueur joueur){
        VBox vBox = new VBox(10);


        Label infoLabel = new Label("Information");
        Label firstNameLabel = new Label("Nom : "+ joueur.getFirstName());
        Label lastNameLabel  = new Label("Prenom : "+ joueur.getLastName());
        Label scoreLabel     = new Label("Score : "+ joueur.getScore());
        Label colorLabel     = new Label("Red");
        colorLabel.setTextFill(Color.web("#fd1111"));

        vBox.getChildren().addAll(infoLabel, firstNameLabel, lastNameLabel, scoreLabel, colorLabel);
        return vBox;
    }
    public VBox createJeu(){

        VBox vBox = new VBox();


        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                buttons[i][j] = new Button(i+" "+j);
                gridPane.add(buttons[i][j], j,i);
            }

        }

//        gridPane.add(button50, 0, 0);
//        gridPane.add(button51, 1, 0);
//        gridPane.add(button52, 2, 0);
//        gridPane.add(button53, 3, 0);
//        gridPane.add(button54, 4, 0);
//        gridPane.add(button55, 5, 0);
//        gridPane.add(button56, 6, 0);
//
//
//        gridPane.add(button40, 0, 1);
//        gridPane.add(button41, 1, 1);
//        gridPane.add(button42, 2, 1);
//        gridPane.add(button43, 3, 1);
//        gridPane.add(button44, 4, 1);
//        gridPane.add(button45, 5, 1);
//        gridPane.add(button46, 6, 1);
//
//        gridPane.add(button30, 0, 2);
//        gridPane.add(button31, 1, 2);
//        gridPane.add(button32, 2, 2);
//        gridPane.add(button33, 3, 2);
//        gridPane.add(button34, 4, 2);
//        gridPane.add(button35, 5, 2);
//        gridPane.add(button36, 6, 2);
//
//        gridPane.add(button20, 0, 3);
//        gridPane.add(button21, 1, 3);
//        gridPane.add(button22, 2, 3);
//        gridPane.add(button23, 3, 3);
//        gridPane.add(button24, 4, 3);
//        gridPane.add(button25, 5, 3);
//        gridPane.add(button26, 6, 3);
//
//        gridPane.add(button10, 0, 4);
//        gridPane.add(button11, 1, 4);
//        gridPane.add(button12, 2, 4);
//        gridPane.add(button13, 3, 4);
//        gridPane.add(button14, 4, 4);
//        gridPane.add(button15, 5, 4);
//        gridPane.add(button16, 6, 4);
//
//        gridPane.add(button00, 0, 5);
//        gridPane.add(button01, 1, 5);
//        gridPane.add(button02, 2, 5);
//        gridPane.add(button03, 3, 5);
//        gridPane.add(button04, 4, 5);
//        gridPane.add(button05, 5, 5);
//        gridPane.add(button06, 6, 5);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        vBox.getChildren().addAll(gridPane);

        return vBox;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }
}
