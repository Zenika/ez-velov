package com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur;


import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurEntity;
import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurEntityMapper;
import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurJpaRepository;
import com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur.UtilisateurStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilisateurStoreTest {

    @InjectMocks
    public UtilisateurStore utilisateurStore;

    @Mock
    public UtilisateurJpaRepository utilisateurJpaRepository;

    @Spy
    public UtilisateurEntityMapper utilisateurEntityMapper;

    @Test
    public void findAll_shouldReturnEmptyList_whenFindAllReturnEmptyList() {
        //given

        //when
        utilisateurStore.findAll();

        //then
        verify(utilisateurEntityMapper, never()).utilisateurEntityToUtilisateur(any());
    }

    @Test
    public void findAll_shouldReturnOnlyOneUtilisateur_whenListUtilisateurSizeIsOne() {
        //given
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        when(utilisateurJpaRepository.findAll()).thenReturn(List.of(utilisateurEntity));

        //when
        utilisateurStore.findAll();

        //then
        verify(utilisateurEntityMapper).utilisateurEntityToUtilisateur(utilisateurEntity);
    }
}
