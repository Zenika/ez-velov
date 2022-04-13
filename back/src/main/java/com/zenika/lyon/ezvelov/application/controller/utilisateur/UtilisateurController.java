package com.zenika.lyon.ezvelov.application.controller.utilisateur;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @GetMapping
    public Utilisateur getUtilisateur() {
        return new Utilisateur("Stranger", "Mister");
    }
}
