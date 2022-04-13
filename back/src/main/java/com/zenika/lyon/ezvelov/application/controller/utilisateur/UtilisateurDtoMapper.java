package com.zenika.lyon.ezvelov.application.controller.utilisateur;

import com.zenika.lyon.ezvelov.domain.utilisateur.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurDtoMapper {

    UtilisateurDto UtilisateurToUtilisateurDto(Utilisateur utilisateur) {
        return new UtilisateurDto(utilisateur.getNom(), utilisateur.getPrenom());
    }

    Utilisateur UtilisateurDtoToUtilisateur(UtilisateurDto utilisateurDto) {
        return new Utilisateur(utilisateurDto.getNom(), utilisateurDto.getPrenom(), null);
    }
}
