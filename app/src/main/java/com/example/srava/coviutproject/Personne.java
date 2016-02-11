package com.example.srava.coviutproject;

/**
 * Created by sanchisf on 11/02/2016.
 */
public class Personne {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;

    public Personne(String nom) {
        this.nom = nom;
    }
}
