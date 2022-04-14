package com.zenika.lyon.ezvelov.domain.utilisateur;

import java.util.Objects;

public class Utilisateur {
    private final String nom;
    private final String prenom;
    private final String email;

    public Utilisateur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, email);
    }
}
