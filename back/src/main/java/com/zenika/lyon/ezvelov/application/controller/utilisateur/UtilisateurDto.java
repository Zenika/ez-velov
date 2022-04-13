package com.zenika.lyon.ezvelov.application.controller.utilisateur;

public class UtilisateurDto {
    private final String nom;
    private final String prenom;

    public UtilisateurDto(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
