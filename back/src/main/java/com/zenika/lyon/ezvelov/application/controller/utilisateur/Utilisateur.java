package com.zenika.lyon.ezvelov.application.controller.utilisateur;

public class Utilisateur {
    private final String prenom;
    private final String nom;

    public Utilisateur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }
}
