package com.zenika.lyon.ezvelov.domain.utilisateur;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur getUtilisateur() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty()) {
            return new Utilisateur("Mister", "Nobody", null);
        } else {
            return utilisateurs.get(0);
        }
    }

}
