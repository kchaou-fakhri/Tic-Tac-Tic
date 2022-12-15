package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Joueur;

public class JeuUI  {

   private GridPane gridPane = new GridPane();
   private Button[][] buttons = new Button[6][7];


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


        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                buttons[i][j] = new Button(i+" "+j);
                gridPane.add(buttons[i][j], j,i);
            }

        }


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

    public void setCouleurButon(int i, int jj, String s) {
        buttons[i][jj].setStyle(s);

    }
}
