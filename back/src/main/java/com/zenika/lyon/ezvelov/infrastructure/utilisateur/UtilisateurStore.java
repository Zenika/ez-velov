package com.zenika.lyon.ezvelov.infrastructure.utilisateur;

import com.zenika.lyon.ezvelov.domain.utilisateur.Utilisateur;
import com.zenika.lyon.ezvelov.domain.utilisateur.IResquestUtilisateurStore;
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

        List<Utilisateur> utilisateurs = new ArrayList<>();
        for (int i = 0; i < utilisateurEntities.size(); i++) {
            utilisateurs.set(i, utilisateurEntityMapper.utilisateurEntityToUtilisateur(utilisateurEntities.get(i)));
        }

        return utilisateurs;
    }
}
