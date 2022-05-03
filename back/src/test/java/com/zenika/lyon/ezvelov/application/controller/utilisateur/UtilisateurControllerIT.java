package com.zenika.lyon.ezvelov.application.controller.utilisateur;

import com.zenika.lyon.ezvelov.EzVelovIT;
import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurEntity;
import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UtilisateurControllerIT extends EzVelovIT {

    @Autowired
    public UtilisateurJpaRepository utilisateurJpaRepository;

    @Test
    public void getUtilisateur_should_return_utilisateur_when_only_one_is_saved() {
        //given
        utilisateurJpaRepository.deleteAll();

        UtilisateurEntity givenUtilisateurEntity = new UtilisateurEntity();
        givenUtilisateurEntity.setNom("LeGrand");
        givenUtilisateurEntity.setPrenom("Bobby");
        utilisateurJpaRepository.save(givenUtilisateurEntity);

        UtilisateurDto expectedUtilisateurDto = new UtilisateurDto("LeGrand", "Bobby");

        //when
        UtilisateurDto result = restTemplate.getForObject(this.uri + "/utilisateur", UtilisateurDto.class);

        //then
        assertThat(result).isEqualTo(expectedUtilisateurDto);

        //clean
        utilisateurJpaRepository.delete(givenUtilisateurEntity);
    }
}
