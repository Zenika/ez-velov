package com.zenika.lyon.ezvelov.domain.utilisateur;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private IResquestUtilisateurStore iResquestUtilisateurStore;

    public UtilisateurService(IResquestUtilisateurStore iResquestUtilisateurStore) {
        this.iResquestUtilisateurStore = iResquestUtilisateurStore;
    }

    public Utilisateur getUtilisateur() {
        List<Utilisateur> utilisateurs = iResquestUtilisateurStore.findAll();
        if (utilisateurs == null || utilisateurs.isEmpty()) {
            return new Utilisateur("Mister", "Nobody", null);
        } else {
            return utilisateurs.get(0);
        }
    }

}
