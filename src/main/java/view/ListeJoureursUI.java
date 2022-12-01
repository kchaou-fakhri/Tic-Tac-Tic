package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Joureur;

import java.util.ArrayList;

public  class ListeJoureursUI {

    ObservableList<Joureur> joureursMoved = FXCollections.observableArrayList();
    ObservableList<Joureur> joureursBack = FXCollections.observableArrayList();

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




    public  TableView<Joureur> createTableViewLeft(ArrayList<Joureur> mockJoureurs){

        TableView.TableViewSelectionModel<Joureur> selectionModel = tableViewLeft.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn<Joureur, String> id = new TableColumn<>("ID");
                 id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Joureur, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Joureur, String> lastName  = new TableColumn<>("Last Name");
                 lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Joureur, Double> score = new TableColumn<>("Score");
                score.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableViewLeft.getColumns().addAll(id, firstName, lastName, score);
        if (mockJoureurs != null){
            ObservableList<Joureur> _mock = FXCollections.observableArrayList(mockJoureurs);
            tableViewLeft.setItems(_mock);
        }


        return tableViewLeft;
    }

    public  TableView<Joureur> createTableViewRight(ArrayList<Joureur> mockJoureurs){

        TableView.TableViewSelectionModel<Joureur> selectionModel = tableViewRight.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn<Joureur, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Joureur, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Joureur, String> lastName  = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Joureur, Double> score = new TableColumn<>("Score");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableViewRight.getColumns().addAll(id, firstName, lastName, score);
        if (mockJoureurs != null){
            ObservableList<Joureur> _mock = FXCollections.observableArrayList(mockJoureurs);
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

    public void addTableViewRight(Joureur joureur) {
        joureursMoved.add(joureur);
        tableViewLeft.getItems().remove(joureur);
        tableViewRight.setItems(joureursMoved);
    }

    public void addAllTableViewRight(ArrayList<Joureur> joureurs) {
        joureursMoved.addAll(joureurs);
        for (Joureur joureur: joureurs) {
            tableViewLeft.getItems().remove(joureur);
        }

        tableViewRight.setItems(joureursMoved);
    }

    public void addTableViewLeft(Joureur joureur) {
        joureursBack.add(joureur);
        tableViewRight.getItems().remove(joureur);
        tableViewLeft.setItems(joureursBack);

        System.out.println(joureursBack.size() +"------"+ joureursMoved.size());
    }

    public void addAllTableViewLeft(ArrayList<Joureur> joureurs) {

        joureursBack.addAll(joureurs);
        for (Joureur joureur: joureurs) {
            tableViewRight.getItems().remove(joureur);
        }

        tableViewLeft.setItems(joureursBack);
    }
}
