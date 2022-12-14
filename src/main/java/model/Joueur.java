package model;

import java.io.Serializable;

public class Joueur implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private Double score;

    public Joueur(){};

    public Joueur(String id, String firstName, String lastName, Double score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }


    public void incrementerScore() {
        this.score++;
    }
    public void decrementerScore() {
        this.score--;
    }
}
