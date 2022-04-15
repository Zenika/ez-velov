package com.zenika.lyon.ezvelov.infrastructure.utilisateur;

import com.zenika.lyon.ezvelov.domain.utilisateur.IResquestUtilisateurStore;
import com.zenika.lyon.ezvelov.domain.utilisateur.Utilisateur;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UtilisateurStore implements IResquestUtilisateurStore {

    private final UtilisateurJpaRepository utilisateurJpaRepository;
    private final UtilisateurEntityMapper utilisateurEntityMapper;

    public UtilisateurStore(UtilisateurJpaRepository utilisateurJpaRepository, UtilisateurEntityMapper utilisateurEntityMapper) {
        this.utilisateurJpaRepository = utilisateurJpaRepository;
        this.utilisateurEntityMapper = utilisateurEntityMapper;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<UtilisateurEntity> utilisateurEntities = utilisateurJpaRepository.findAll();

        return utilisateurEntities.stream()
                .map(utilisateurEntityMapper::utilisateurEntityToUtilisateur)
                .toList();
    }
}
