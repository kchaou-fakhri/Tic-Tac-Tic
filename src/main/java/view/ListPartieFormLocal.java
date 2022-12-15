package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Joueur;
import model.Partie;

import java.util.ArrayList;

public class ListPartieFormLocal {

    TableView tableViewListPartie;

    public ListPartieFormLocal() {
       tableViewListPartie = new TableView<>();
    }

    public VBox getListJoueur(ArrayList<String> parties){

        VBox vBox = new VBox();



        TableColumn<String, String> id = new TableColumn<>("ID");




        tableViewListPartie.getColumns().addAll(id);

        id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        if (parties != null){
            ObservableList<String> _parties = FXCollections.observableArrayList(parties);
            tableViewListPartie.setItems(_parties);

        }

        vBox.getChildren().add(tableViewListPartie);

        return vBox;
    }


    public TableView getTableViewListPartie() {
        return tableViewListPartie;
    }

    public void setTableViewListPartie(TableView tableViewListPartie) {
        this.tableViewListPartie = tableViewListPartie;
    }
}
