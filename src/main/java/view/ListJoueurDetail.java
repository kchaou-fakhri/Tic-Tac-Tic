package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Joueur;

import java.util.ArrayList;

public class ListJoueurDetail {

    TableView tableViewListJoueur;

    Button deleteJoueur;


    public ListJoueurDetail() {
        this.tableViewListJoueur = new TableView();
        deleteJoueur = new Button("Delete");
        deleteJoueur.setStyle("-fx-background-color: #fd0202; -fx-text-fill: #ffffff");
        deleteJoueur.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    }

    public VBox getListJoueur(ArrayList<Joueur> joueurs){

        VBox vBox = new VBox();



        TableColumn<Joueur, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Joueur, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Joueur, String> lastName  = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Joueur, Double> score = new TableColumn<>("Score");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableViewListJoueur.getColumns().addAll(id, firstName, lastName, score);
        if (joueurs != null){
            ObservableList<Joueur> _joueurs = FXCollections.observableArrayList(joueurs);
            tableViewListJoueur.setItems(_joueurs);
        }

        vBox.getChildren().add(tableViewListJoueur);

        return vBox;
    }


    public TableView getTableViewListJoueur() {
        return tableViewListJoueur;
    }

    public void setTableViewListJoueur(TableView tableViewListJoueur) {
        this.tableViewListJoueur = tableViewListJoueur;
    }

    public VBox displayDetailJoueurSelected(Joueur joueur) {
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

    public Button getDeleteJoueur() {
        return deleteJoueur;
    }

    public void setDeleteJoueur(Button deleteJoueur) {
        this.deleteJoueur = deleteJoueur;
    }
}
