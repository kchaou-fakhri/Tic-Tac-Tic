package model;

import DAOJoueur.JoueurDAO;
import java.util.ArrayList;
import java.util.List;


public class Partie {
    private Joueur j1, j2;
    private ArrayList<Coup> lisCoupJ = new ArrayList();
    private int nbPionJ1 = 21;
    private int nbPionJ2 = 21;
    private int scorej1, scorej2;
    private int rolejoueur;
    private Puissance puissance;
    private ArrayList<Coup> lisCoupJ1, lisCoupJ2;

    public Partie() {
        JoueurDAO dAOJoueur = new JoueurDAO();
        List<Joueur> listeJoueur = dAOJoueur.getAll();
        j1 = listeJoueur.get(1);
        j2 = listeJoueur.get(2);
        this.rolejoueur = Integer.parseInt(j1.getId());
        puissance = new Puissance(Integer.parseInt(j1.getId()), Integer.parseInt(j2.getId()));
    }

    public Partie(Joueur j1, Joueur j2) {
        this.j1 = j1;
        this.j2 = j2;
        lisCoupJ1 = new ArrayList<>();
        lisCoupJ2 = new ArrayList<>();
        this.rolejoueur = Integer.parseInt(j1.getId());
        puissance = new Puissance(Integer.parseInt(j1.getId()), Integer.parseInt(j2.getId()));
    }

    @Override
    public String toString() {
        String ch = "Partie entre Joueur: " + j1 + " et Joueur: " + j2;
        switch (scorej1) {
            case 0:
                ch = ch + "  est null";
                break;
            case 1:
                ch = ch + "\n" + j1 + " est gagnant";
                break;
            case -1:
                ch = ch + "\n" + j2 + " est gagnant";
                break;
        }
        return ch;
    }

    public void modifieRole() {
        if (this.rolejoueur == Integer.parseInt(this.j1.getId()))
            this.rolejoueur = Integer.parseInt(this.j2.getId());
        else
            this.rolejoueur = Integer.parseInt(this.j1.getId());
    }

    public int getRolejoueur() {
        return rolejoueur;
    }

    public void setCoup(int numLigne, int numColonne) {
        this.puissance.setCoup(numLigne, numColonne, getRolejoueur());
        insertCoup(new Coup(numColonne));
    }

	public void insertCoup(Coup coup) {
		if (this.rolejoueur == Integer.parseInt(this.j1.getId())) {
			this.lisCoupJ1.add(coup);
			nbPionJ1--;
		} else {
			this.lisCoupJ2.add(coup);
			nbPionJ1--;
		}
	}

    public int getScoreJ2() {
        return scorej2;
    }

    public void setScoreJ2(int scorej2) {
        this.scorej2 = scorej2;
    }

    public int getScoreJ1() {
        return scorej1;
    }

    public void setScoreJ1(int scorej1) {
        this.scorej1 = scorej1;
    }

    public int getNbPionJ1() {
        return nbPionJ1;
    }

    public void setNbPionJ1(int nbPionJ1) {
        this.nbPionJ1 = nbPionJ1;
    }

    public int getNbPionJ2() {
        return nbPionJ2;
    }

    public void setNbPionJ2(int nbPionJ2) {
        this.nbPionJ2 = nbPionJ2;
    }

    public Puissance getPuissance() {
        return puissance;
    }

    public void setPuissance(Puissance p) {
        this.puissance = p;
    }

    public Joueur getJ1() {
        return j1;
    }

    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }

    public Joueur getJ2() {
        return j2;
    }

    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    public String getNomCourant() {
        if (this.rolejoueur == Integer.parseInt(this.j1.getId()))
            return j1.getFirstName();
        else
            return j2.getFirstName();
    }

    public int getLigneVideByColonne(int jj) {
        return this.puissance.getLigneVideByColonne(jj);
    }

    public boolean estGagnant(Position position) {
        return this.puissance.estGagnant(position, rolejoueur);
    }

    public void initialiseGrille() {
        this.puissance.initialiseGrille();
    }

    public boolean estRemplie() {
        return this.puissance.estRemplie();
    }
}