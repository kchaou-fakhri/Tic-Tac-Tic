package IDAO;

import model.Joureur;

import java.util.ArrayList;

public interface JoueurIDAO {

    Boolean insertJoueur(Joureur joureur);
    Joureur getById(String id);
    ArrayList<Joureur> getAll();
}
