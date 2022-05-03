package com.zenika.lyon.ezvelov.application.controller.utilisateur;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UtilisateurDto {
    private final String nom;
    private final String prenom;

    public UtilisateurDto(@JsonProperty("nom") String nom, @JsonProperty("prenom") String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurDto that = (UtilisateurDto) o;
        return Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom);
    }

    @Override
    public String toString() {
        return "UtilisateurDto{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
