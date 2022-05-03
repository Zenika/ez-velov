package com.zenika.lyon.ezvelov.application.controller.utilisateur;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.zenika.lyon.ezvelov.EzVelovIT;
import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurEntity;
import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dbunit/basic_state.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dbunit/delete_all.xml")
public class UtilisateurControllerIT extends EzVelovIT {

    @Test
    public void getUtilisateur_should_return_utilisateur_when_only_one_is_saved() {
        //given
        UtilisateurDto expectedUtilisateurDto = new UtilisateurDto("LeGrand", "Bobby");

        //when
        UtilisateurDto result = restTemplate.getForObject(this.uri + "/utilisateur", UtilisateurDto.class);

        //then
        assertThat(result).isEqualTo(expectedUtilisateurDto);
    }
}
