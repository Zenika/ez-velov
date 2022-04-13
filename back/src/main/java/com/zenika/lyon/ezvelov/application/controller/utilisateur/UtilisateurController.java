package com.zenika.lyon.ezvelov.application.controller.utilisateur;

import com.zenika.lyon.ezvelov.domain.utilisateur.UtilisateurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurDtoMapper utilisateurDtoMapper;

    private UtilisateurController(UtilisateurService utilisateurService, UtilisateurDtoMapper utilisateurDtoMapper) {
        this.utilisateurService = utilisateurService;
        this.utilisateurDtoMapper = utilisateurDtoMapper;
    }

    @GetMapping
    public UtilisateurDto getUtilisateur() {
        return utilisateurDtoMapper.UtilisateurToUtilisateurDto(utilisateurService.getUtilisateur());
    }
}
