package controller;

import DAOJoueur.JoueurDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Joueur;
import view.JeuUI;
import view.ListJoueurDetail;
import view.ListeJoureursUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JeuController {

    private MainApplication mainApplication;
    private ListeJoureursUI listeJoureursUI;
    private JeuUI jeuUI;
    private ListJoueurDetail listJoueurDetail;



    ArrayList<Joueur> mockJoueurs = new ArrayList<>();
    ArrayList<Joueur> mockJoureursMoved = new ArrayList<>();

    JoueurDAO joueurDAO;

    Scene scene;
    MenuBar menuBar;
    BorderPane borderPane;
    Menu gestionJeuMenu;
    Menu gestionPartie;
    Menu statistique;
    Menu gestionprofil;
    Menu help;



    public JeuController() throws IOException {
        joueurDAO = new JoueurDAO();

        menuBar = new MenuBar();
        borderPane = new BorderPane();
        jeuUI = new JeuUI();
        listeJoureursUI = new ListeJoureursUI();
        createMenu();
        listJoueurDetail = new ListJoueurDetail();


//        joueurDAO.insert(new Joueur("001","Ahmed","Kchaou",9.5));
//        joueurDAO.insert(new Joueur("002","Fakhri","Kchaou",5.5));



    }


    public void createMenu()  {


        /***************** create the options of menu ***************************/
        gestionJeuMenu = new Menu("Gestion Jeu");
            MenuItem lancerJeuMenuItem          = new MenuItem("Lancer Jeu");
            MenuItem liste_joueur_avec_detail  = new MenuItem("Liste Joueur avec Detail");
            MenuItem quitterMenuItem            = new MenuItem("Quitter");
                gestionJeuMenu.getItems().addAll(lancerJeuMenuItem, liste_joueur_avec_detail, quitterMenuItem);

        gestionPartie  = new Menu("Gestion Partie");
            MenuItem listePartiesMenuItem       = new MenuItem("La liste des parties");
            MenuItem simulationMenuItem         = new MenuItem("Simulation d'une Partie");
            MenuItem importExportMenuItem       = new MenuItem("Importer et Exporte d'une  partie");
                gestionPartie.getItems().addAll(listePartiesMenuItem, simulationMenuItem, importExportMenuItem);

        statistique    = new Menu("Statistique");
            MenuItem listeJoueurMenuItem       = new MenuItem("Liste Joueur");
            MenuItem jourousSelonScoreMenuItem = new MenuItem("La liste des joureus selon score");
                statistique.getItems().addAll(listeJoueurMenuItem, jourousSelonScoreMenuItem);

        gestionprofil  = new Menu("Gestion Profil");
        help           = new Menu("Help");


        /***************** add options menu to MenuBar ***************************/

        menuBar.getMenus().addAll(gestionJeuMenu,gestionPartie, statistique, gestionprofil, help);
        var tt =((Joueur) joueurDAO.getById("002"));
        var userInfoLeft = jeuUI.createUserInfoLeft(tt);
        var userInfoRight = jeuUI.createUserInfoRight((Joueur) joueurDAO.getById("001"));

        borderPane.setTop(menuBar);




        VBox actionBar = listeJoureursUI.createActionBar();



        Insets insets = new Insets(5);







        lancerJeuMenuItem.setOnAction(e -> {

            borderPane.setCenter(jeuUI.createJeu());
            borderPane.setLeft(userInfoLeft);
            borderPane.setRight(userInfoRight);

            BorderPane.setMargin(userInfoRight, insets);

            setListnerofGames();
        });

        listeJoueurMenuItem.setOnAction(e->{
            borderPane.setLeft(listeJoureursUI.createTableViewLeft((ArrayList<Joueur>)joueurDAO.getAll()));
            borderPane.setRight(listeJoureursUI.createTableViewRight(mockJoureursMoved));
            borderPane.setCenter(actionBar);
            BorderPane.setMargin(actionBar, insets);

            /******************* set listener on listeJoureurs *****************/
            setListnerOnListeJoureursView();

        });

        liste_joueur_avec_detail.setOnAction(e->{


            borderPane.setLeft(listJoueurDetail.getListJoueur((ArrayList<Joueur>)joueurDAO.getAll()));
            setListnerOfListDetailJoueurs();
         //   borderPane.setBottom(listJoueurDetail.getDeleteJoueur());
            BorderPane.setMargin(borderPane.getLeft(), insets);




        });
    }



    public Parent getBorderPane() {
        return borderPane;
    }


    private void setListnerOnListeJoureursView(){
        listeJoureursUI.getMoveButton().setOnAction(e ->{
            Joueur joueur = (Joueur) listeJoureursUI.getTableViewLeft().getSelectionModel().getSelectedItem();
            if (joueur != null){
                mockJoureursMoved.add(joueur);
                listeJoureursUI.addTableViewRight(joueur);
                mockJoueurs.remove(joueur);

            }

        });

        listeJoureursUI.getMoveAllButton().setOnAction(e ->{
            ObservableList joureurs = listeJoureursUI.getTableViewLeft().getSelectionModel().getSelectedItems();
            if (joureurs != null){
                mockJoureursMoved.addAll(joureurs);
                listeJoureursUI.addAllTableViewRight((ArrayList<Joueur>) joureurs.stream().collect(Collectors.toList()));
                for (Joueur _joueur : (ArrayList<Joueur>) joureurs.stream().collect(Collectors.toList())) {
                    mockJoueurs.remove(_joueur);
                }
            }

        });


        listeJoureursUI.getBackButton().setOnAction(e ->{
            Joueur joueur = (Joueur) listeJoureursUI.getTableViewRight().getSelectionModel().getSelectedItem();
            if (joueur != null){
                mockJoueurs.add(joueur);
                listeJoureursUI.addTableViewLeft(joueur);

            }

        });

        listeJoureursUI.getBackAllButton().setOnAction(e ->{
            ObservableList joureurs = listeJoureursUI.getTableViewRight().getSelectionModel().getSelectedItems();
            if (joureurs != null){
                mockJoueurs.addAll(joureurs);
                listeJoureursUI.addAllTableViewLeft((ArrayList<Joueur>) joureurs.stream().collect(Collectors.toList()));
                for (Joueur _joueur : (ArrayList<Joueur>) joureurs.stream().collect(Collectors.toList())) {
                    mockJoureursMoved.remove(_joueur);
                }
            }

        });
    }


    /******************************* Display joueur selected on Liste Joueur Detail ***************/
    private void setListnerOfListDetailJoueurs(){
        listJoueurDetail.getTableViewListJoueur().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

        Joueur joueur = (Joueur) listJoueurDetail.getTableViewListJoueur().getSelectionModel().getSelectedItem();
        if (joueur != null){

            borderPane.setCenter(listJoueurDetail.displayDetailJoueurSelected(joueur));
            BorderPane.setMargin(borderPane.getCenter(), new Insets(15));


        }
        });


    }



    private void setListnerofGames() {
        AtomicInteger user = new AtomicInteger(1);
        Button[][] buttons = jeuUI.getButtons();
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                int finalI = i;
                int finalJ = j;
                buttons[j][i].setOnAction(e->{
                    if (user.get() ==1){
                        buttons[finalJ][finalI].setStyle("-fx-background-color: #fd0202; -fx-text-fill: #ffffff");
                        user.set(0);
                    }
                    else {
                        buttons[finalJ][finalI].setStyle("-fx-background-color: #0df1da; -fx-text-fill: #ffffff");
                        user.set(1);

                    }

               });
            }

        }
    }





}
