package model;

import java.util.ArrayList;
import java.util.List;

public class Puissance {
    private int nbLigne = 6;
    private int nbColonne = 7;
    private int[][] grille = new int[6][7];
    private final int idJ1, idJ2;

    public Puissance(final int j1, final int j2) {
        idJ1 = j1;
        idJ2 = j2;
    }

    public Puissance(Puissance puissance) {

        for (int j = 0; j < nbColonne; j++)
            for (int i = 0; i < nbLigne; i++)
                grille[i][j] = puissance.getValeurPosition(i, j);
        idJ1 = puissance.getIdJ1();
        idJ2 = puissance.getIdJ2();
        nbLigne = puissance.nbLigne;
        nbColonne = puissance.nbColonne;

    }

    public int getNbLigne() {
        return nbLigne;
    }

    public int getNbColonne() {
        return nbColonne;
    }

    public int getValeurPosition(int numLigne, int numColonne) {
        return grille[numLigne][numColonne];
    }

    public boolean estPositionVide(int numLigne, int numColonne) {
        if (grille[numLigne][numColonne] == 0)
            return true;
        return false;
    }

    public boolean setCoup(int posL, int posC, int idJoueur) {
        grille[posL][posC] = idJoueur;
        return estGagnant(posL, posC, idJoueur);

    }

    public boolean setCoup(Position pos, int idJoueur) {
        grille[pos.getPosX()][pos.getPosY()] = idJoueur;
        return estGagnant(pos, idJoueur);

    }

    public boolean estGagnant(int posL, int posC, int idJoueur) {
        boolean fin = false;
        Position pos = new Position(posL, posC);
        fin = alignementH(pos, idJoueur) || alignementV(pos, idJoueur) || alignementD(pos, idJoueur);
        return fin;
    }

    public boolean estGagnant(Position pos, int idJoueur) {
        boolean fin = false;
        fin = alignementH(pos, idJoueur) || alignementV(pos, idJoueur) || alignementD(pos, idJoueur);
        return fin;
    }

    private boolean alignementD(Position pos, int idJoueur) {
        int k = 0;
        int j = pos.getPosY();
        int i = pos.getPosX();
        while (j >= 0 && i >= 0 && grille[i][j] == idJoueur) {
            k++;
            j--;
            i--;
        }
        j = pos.getPosY() + 1;
        i = pos.getPosX() + 1;
        while (j < nbColonne && i < nbLigne && grille[i][j] == idJoueur) {
            k++;
            j++;
            i++;
        }
        if (k >= 4)
            return true;
        ////
        k = 0;
        j = pos.getPosY();
        i = pos.getPosX();
        while (j >= 0 && i < nbLigne && grille[i][j] == idJoueur) {
            k++;
            j--;
            i++;
        }
        j = pos.getPosY() + 1;
        i = pos.getPosX() - 1;
        while (j < nbColonne && i >= 0 && grille[i][j] == idJoueur) {
            k++;
            j++;
            i--;
        }
        if (k >= 4)
            return true;
        return false;
    }

    private boolean alignementV(Position pos, int idJoueur) {
        int k = 0;
        int i = pos.getPosX();
        while (i >= 0 && grille[i][pos.getPosY()] == idJoueur) {
            k++;
            i--;
        }
        if (k == 4)
            return true;
        return false;
    }

    private boolean alignementH(Position pos, int idJoueur) {
        int k = 0;
        int j = pos.getPosY();
        while (j >= 0 && grille[pos.getPosX()][j] == idJoueur) {
            k++;
            j--;
        }
        j = pos.getPosY() + 1;
        while (j < 7 && grille[pos.getPosX()][j] == idJoueur) {
            k++;
            j++;
        }
        if (k >= 4)
            return true;
        return false;
    }

    public int getLigneVideByColonne(int numColonne) {
        int numLigne = 0;
        while (numLigne < 6 && !estPositionVide(numLigne, numColonne))
            numLigne++;
        if(numLigne==6)
            return -1;
        return numLigne;
    }

    public void initialiseGrille() {
        for (int i = 0; i < nbLigne; i++)
            for (int j = 0; j < nbColonne; j++)
                grille[i][j] = 0;
    }

    public String toString() {
        String ch = "";
        for (int i = nbLigne - 1; i >= 0; i--) {
            ch = ch + (i + 1) + " ";
            for (int j = 0; j < nbColonne; j++) {
                String car = "-";
                if (grille[i][j] == idJ1)
                    car = "+";
                else if (grille[i][j] == idJ2)
                    car = "*";
                ch = ch + "| " + car;
            }
            ch = ch + "|\n";
        }
        ch = ch + "  ";
        for (int j = 0; j < nbColonne; j++)
            ch = ch + "| " + (j + 1);
        return ch;
    }


    ////
    public int getIdJ1() {
        return idJ1;
    }

    public int getIdJ2() {
        return idJ2;
    }
    //
    public boolean estRemplie() {
        boolean test=true;
        for (int i = 0; i < nbLigne && test; i++)
            for (int j = 0; j < nbColonne && test; j++)
                if(grille[i][j] ==0) {
                    test=false;
                    break;
                }
        if (test==false)
            return false;
        return true;
    }

}