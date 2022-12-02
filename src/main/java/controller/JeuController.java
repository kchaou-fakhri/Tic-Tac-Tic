package controller;

import DAOJoueur.JoueurDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Joureur;
import model.UtilitairesBD;
import view.JeuUI;
import view.ListeJoureursUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JeuController {

    private MainApplication mainApplication;
    private ListeJoureursUI listeJoureursUI;
    private JeuUI jeuUI;



    ArrayList<Joureur> mockJoureurs = new ArrayList<>();
    ArrayList<Joureur> mockJoureursMoved = new ArrayList<>();

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



       // joueurDAO.insert(new Joureur("002","Fakhri","Kchaou",5.5));



    }


    public void createMenu()  {


        /***************** create the options of menu ***************************/
        gestionJeuMenu = new Menu("Gestion Jeu");
            MenuItem lancerJeuMenuItem          = new MenuItem("Lancer Jeu");
            MenuItem lancerJeuContinueMenuItem  = new MenuItem("Lancer Jeu");
            MenuItem quitterMenuItem            = new MenuItem("Quitter");
                gestionJeuMenu.getItems().addAll(lancerJeuMenuItem, lancerJeuContinueMenuItem, quitterMenuItem);

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
        var tt =((Joureur) joueurDAO.getById("002"));
        var userInfoLeft = jeuUI.createUserInfoLeft(tt);
        var userInfoRight = jeuUI.createUserInfoRight((Joureur) joueurDAO.getById("002"));

        borderPane.setTop(menuBar);




        VBox actionBar = listeJoureursUI.createActionBar();



        Insets insets = new Insets(5);





        lancerJeuMenuItem.setOnAction(e -> {

            borderPane.setCenter(jeuUI.createJeu());
            borderPane.setLeft(userInfoLeft);
            borderPane.setRight(userInfoRight);

            BorderPane.setMargin(userInfoRight, insets);
        });

        listeJoueurMenuItem.setOnAction(e->{
            borderPane.setLeft(listeJoureursUI.createTableViewLeft((ArrayList<Joureur>)joueurDAO.getAll()));
            borderPane.setRight(listeJoureursUI.createTableViewRight(mockJoureursMoved));
            borderPane.setCenter(actionBar);
            BorderPane.setMargin(actionBar, insets);

            /******************* set listener on listeJoureurs *****************/
            setListnerOnListeJoureursView();

        });
    }

    public Parent getBorderPane() {
        return borderPane;
    }


    private void setListnerOnListeJoureursView(){
        listeJoureursUI.getMoveButton().setOnAction(e ->{
            Joureur joureur = (Joureur) listeJoureursUI.getTableViewLeft().getSelectionModel().getSelectedItem();
            if (joureur != null){
                mockJoureursMoved.add(joureur);
                listeJoureursUI.addTableViewRight( joureur);
                mockJoureurs.remove(joureur);

            }

        });

        listeJoureursUI.getMoveAllButton().setOnAction(e ->{
            ObservableList joureurs = listeJoureursUI.getTableViewLeft().getSelectionModel().getSelectedItems();
            if (joureurs != null){
                mockJoureursMoved.addAll(joureurs);
                listeJoureursUI.addAllTableViewRight((ArrayList<Joureur>) joureurs.stream().collect(Collectors.toList()));
                for (Joureur _joureur: (ArrayList<Joureur>) joureurs.stream().collect(Collectors.toList())) {
                    mockJoureurs.remove(_joureur);
                }
            }

        });


        listeJoureursUI.getBackButton().setOnAction(e ->{
            Joureur joureur = (Joureur) listeJoureursUI.getTableViewRight().getSelectionModel().getSelectedItem();
            if (joureur != null){
                mockJoureurs.add(joureur);
                listeJoureursUI.addTableViewLeft( joureur);

            }

        });

        listeJoureursUI.getBackAllButton().setOnAction(e ->{
            ObservableList joureurs = listeJoureursUI.getTableViewRight().getSelectionModel().getSelectedItems();
            if (joureurs != null){
                mockJoureurs.addAll(joureurs);
                listeJoureursUI.addAllTableViewLeft((ArrayList<Joureur>) joureurs.stream().collect(Collectors.toList()));
                for (Joureur _joureur: (ArrayList<Joureur>) joureurs.stream().collect(Collectors.toList())) {
                    mockJoureursMoved.remove(_joureur);
                }
            }

        });
    }




}
