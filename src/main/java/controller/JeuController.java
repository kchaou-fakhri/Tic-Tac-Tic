package controller;

import DAOJoueur.JoueurDAO;

import com.google.gson.Gson;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Coup;
import model.Joueur;
import model.Partie;
import model.Position;
import view.JeuUI;
import view.ListJoueurDetail;
import view.ListeJoureursUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JeuController {

    private MainApplication mainApplication;
    private ListeJoureursUI listeJoureursUI;
    private JeuUI jeuUI;
    private ListJoueurDetail listJoueurDetail;
    private Partie partie;

    private Joueur joueur_1, joueur_2;





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



    public JeuController() {
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

            joueur_1 =((Joueur) joueurDAO.getById("001"));
            joueur_2 =((Joueur) joueurDAO.getById("002"));


        var userInfoLeft = jeuUI.createUserInfoLeft(joueur_1);
        var userInfoRight = jeuUI.createUserInfoRight(joueur_2);

        borderPane.setTop(menuBar);




        VBox actionBar = listeJoureursUI.createActionBar();



        Insets insets = new Insets(5);







        lancerJeuMenuItem.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setLeft(null);
            borderPane.setRight(null);


            borderPane.setCenter(jeuUI.createJeu());
            borderPane.setLeft(userInfoLeft);
            borderPane.setRight(userInfoRight);
            BorderPane.setMargin(userInfoRight, insets);
            setListnerofGames();

            partie = new Partie(joueur_1, joueur_2);
        });

        listeJoueurMenuItem.setOnAction(e->{
            borderPane.setCenter(null);
            borderPane.setLeft(null);
            borderPane.setRight(null);
            borderPane.setLeft(listeJoureursUI.createTableViewLeft((ArrayList<Joueur>)joueurDAO.getAll()));
            borderPane.setRight(listeJoureursUI.createTableViewRight(mockJoureursMoved));
            borderPane.setCenter(actionBar);
            BorderPane.setMargin(actionBar, insets);

            /******************* set listener on listeJoureurs *****************/
            setListnerOnListeJoureursView();

        });

        liste_joueur_avec_detail.setOnAction(e->{

            borderPane.setCenter(null);
            borderPane.setLeft(null);
            borderPane.setRight(null);
            borderPane.setLeft(listJoueurDetail.getListJoueur((ArrayList<Joueur>)joueurDAO.getAll()));
            setListnerOfListDetailJoueurs();
         //   borderPane.setBottom(listJoueurDetail.getDeleteJoueur());
            BorderPane.setMargin(borderPane.getLeft(), insets);




        });

        quitterMenuItem.setOnAction(e->{

            Gson gson = new Gson();
            try {
               var tt=  gson.toJson(partie);
                gson.toJson(123.45, new FileWriter("src\\main\\partie.json"));

                gson.toJson(1556, new FileWriter("partie.json"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++) {
                final int ii = i;
                final int jj = j;
                // interfaceJeuPuissance.setCouleurButon(ii, jj, "#FFFF00");
                buttons[i][j].setOnAction(event -> {
                    gestionAction(jj);
                });
            }
        
        
        
    }


    private void gestionAction(int jj) {
        int numligne = this.partie.getLigneVideByColonne(jj);
        if (numligne != -1) {
            this.partie.setCoup(numligne, jj);

            if (this.partie.getRolejoueur() == Integer.parseInt(this.joueur_1.getId()))

            jeuUI.setCouleurButon(5 - numligne, jj, "-fx-background-color: #105652; -fx-text-fill: #ffffff");
            else
                jeuUI.setCouleurButon(5 - numligne, jj, "-fx-background-color: #B22727; -fx-text-fill: #ffffff");

            if (this.partie.estGagnant(new Position(numligne, jj))) {
                Alert iBox = new Alert(Alert.AlertType.INFORMATION);
                iBox.setHeaderText("PARTIE FINIE");
                iBox.setContentText("Le joueur " + this.partie.getNomCourant() + " est le gagant ");
                iBox.showAndWait();
                if (this.partie.getRolejoueur() == Integer.parseInt(this.joueur_1.getId())) {
                    this.joueur_1.incrementerScore();
                    this.joueur_2.decrementerScore();
                }else {
                    this.joueur_2.incrementerScore();
                    this.joueur_1.decrementerScore();
                }
                this.partie.initialiseGrille();
                        setListnerofGames();
            } else {
                if (this.partie.estRemplie()) {
                    Alert cBox = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + " ?", ButtonType.YES, ButtonType.CANCEL);
                    cBox.setHeaderText("PARTIE NULL");
                    cBox.setContentText("La grille est remplie \n Voulez-vous rejouer?");
                    cBox.showAndWait();
                    if (cBox.getResult() == ButtonType.YES) {
                        this.partie.getPuissance().initialiseGrille();
                        setListnerofGames();
                    }
                } else
                    this.partie.modifieRole();
            }
        } else {
            Alert xBox = new Alert(Alert.AlertType.ERROR);
            xBox.setHeaderText("La colonne " + jj + " est remplie");
            xBox.showAndWait();
        }
    }



}
