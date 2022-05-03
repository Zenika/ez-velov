package com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur;

import com.zenika.lyon.ezvelov.domain.utilisateur.IResquestUtilisateurStore;
import com.zenika.lyon.ezvelov.domain.utilisateur.Utilisateur;
import com.zenika.lyon.ezvelov.domain.utilisateur.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @InjectMocks
    public UtilisateurService utilisateurService;

    @Mock
    public IResquestUtilisateurStore iResquestUtilisateurStore;

    @Test
    public void getUtilisateur_ShouldReturnNobody_WhenFindAllIsEmpty() {
        // given
        Utilisateur nobody = new Utilisateur("Mister", "Nobody", null);
        //when
        Utilisateur utilisateur = utilisateurService.getUtilisateur();
        //then
        assertThat(utilisateur).isEqualTo(nobody);
    }

    @Test
    public void getUtilisateur_shouldReturnFirstElement_WhenFindAllIsNotEmpty() {
        //given
        Utilisateur connue = new Utilisateur("Mister", "Michel", null);
        Utilisateur connue2 = new Utilisateur("Mister2", "Michel2", null);
        Utilisateur connue3 = new Utilisateur("Mister3", "Michel3", null);
        when(iResquestUtilisateurStore.findAll()).thenReturn(List.of(connue, connue2, connue3));

        //when
        Utilisateur utilisateur = utilisateurService.getUtilisateur();

        //then
        assertThat(utilisateur).isEqualTo(connue);
    }

    @Test
    public void getUtilisateur_shouldReturnNobody_WhenFindAllReturnNull() {
        //given
        Utilisateur nobody = new Utilisateur("Mister", "Nobody", null);
        when(iResquestUtilisateurStore.findAll()).thenReturn(null);

        //when
        Utilisateur utilisateur = utilisateurService.getUtilisateur();

        //then
        assertThat(utilisateur).isEqualTo(nobody);
    }
}
