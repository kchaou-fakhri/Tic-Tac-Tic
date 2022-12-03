package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Joueur;

import java.util.ArrayList;

public  class ListeJoureursUI {

    ObservableList<Joueur> joureursMoved = FXCollections.observableArrayList();
    ObservableList<Joueur> joureursBack = FXCollections.observableArrayList();

    TableView tableViewLeft;

    TableView tableViewRight;
    Button moveButton;
    Button backButton;

    Button moveAllButton;
    Button backAllButton;
    public ListeJoureursUI(){
        tableViewLeft = new TableView();
        tableViewRight = new TableView();
        moveButton = new Button("-> ");
        backButton = new Button("<- ");
        moveAllButton = new Button("=>");
        backAllButton = new Button("<=");
    }




    public  TableView<Joueur> createTableViewLeft(ArrayList<Joueur> mockJoueurs){

        TableView.TableViewSelectionModel<Joueur> selectionModel = tableViewLeft.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn<Joueur, String> id = new TableColumn<>("ID");
                 id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Joueur, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Joueur, String> lastName  = new TableColumn<>("Last Name");
                 lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Joueur, Double> score = new TableColumn<>("Score");
                score.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableViewLeft.getColumns().addAll(id, firstName, lastName, score);
        if (mockJoueurs != null){
            ObservableList<Joueur> _mock = FXCollections.observableArrayList(mockJoueurs);
            tableViewLeft.setItems(_mock);
        }


        return tableViewLeft;
    }

    public  TableView<Joueur> createTableViewRight(ArrayList<Joueur> mockJoueurs){

        TableView.TableViewSelectionModel<Joueur> selectionModel = tableViewRight.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn<Joueur, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Joueur, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Joueur, String> lastName  = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Joueur, Double> score = new TableColumn<>("Score");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableViewRight.getColumns().addAll(id, firstName, lastName, score);
        if (mockJoueurs != null){
            ObservableList<Joueur> _mock = FXCollections.observableArrayList(mockJoueurs);
            tableViewRight.setItems(_mock);
        }


        return tableViewRight;
    }

    public VBox createActionBar(){

        VBox vBox = new VBox();



        vBox.getChildren().addAll(moveButton, backButton, moveAllButton, backAllButton);



        return vBox;
    }


    public TableView getTableViewLeft() {
        return tableViewLeft;
    }

    public TableView getTableViewRight() {
        return tableViewRight;
    }

    public Button getMoveButton() {
        return moveButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getMoveAllButton() {
        return moveAllButton;
    }

    public Button getBackAllButton() {
        return backAllButton;
    }

    public void addTableViewRight(Joueur joueur) {
        joureursMoved.add(joueur);
        tableViewLeft.getItems().remove(joueur);
        tableViewRight.setItems(joureursMoved);
    }

    public void addAllTableViewRight(ArrayList<Joueur> joueurs) {
        joureursMoved.addAll(joueurs);
        for (Joueur joueur : joueurs) {
            tableViewLeft.getItems().remove(joueur);
        }

        tableViewRight.setItems(joureursMoved);
    }

    public void addTableViewLeft(Joueur joueur) {
        joureursBack.add(joueur);
        tableViewRight.getItems().remove(joueur);
        tableViewLeft.setItems(joureursBack);

        System.out.println(joureursBack.size() +"------"+ joureursMoved.size());
    }

    public void addAllTableViewLeft(ArrayList<Joueur> joueurs) {

        joureursBack.addAll(joueurs);
        for (Joueur joueur : joueurs) {
            tableViewRight.getItems().remove(joueur);
        }

        tableViewLeft.setItems(joureursBack);
    }
}
