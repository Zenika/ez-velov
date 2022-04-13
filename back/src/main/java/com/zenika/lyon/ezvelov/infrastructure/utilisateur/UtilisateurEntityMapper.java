package com.zenika.lyon.ezvelov.infrastructure.utilisateur;

import com.zenika.lyon.ezvelov.domain.utilisateur.Utilisateur;
import org.springframework.stereotype.Component;

@Component
class UtilisateurEntityMapper {

    Utilisateur utilisateurEntityToUtilisateur(UtilisateurEntity utilisateurEntity){
        return new Utilisateur(utilisateurEntity.getNom(), utilisateurEntity.getPrenom(), utilisateurEntity.getEmail());
    }

    UtilisateurEntity utilisateurToUtilisateurEntity(Utilisateur utilisateur){
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setNom(utilisateur.getNom());
        utilisateurEntity.setPrenom(utilisateur.getPrenom());
        utilisateurEntity.setEmail(utilisateur.getEmail());
        return utilisateurEntity;
    }
}
