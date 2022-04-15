package com.zenika.lyon.ezvelov.domain.utilisateur;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UtilisateurService {

    private IResquestUtilisateurStore iResquestUtilisateurStore;

    public UtilisateurService(IResquestUtilisateurStore iResquestUtilisateurStore) {
        this.iResquestUtilisateurStore = iResquestUtilisateurStore;
    }

    public Utilisateur getUtilisateur() {
        List<Utilisateur> utilisateurs = iResquestUtilisateurStore.findAll();

        return CollectionUtils.isEmpty(utilisateurs)
                ? new Utilisateur("Mister", "Nobody", null)
                : utilisateurs.get(0);
    }

}
