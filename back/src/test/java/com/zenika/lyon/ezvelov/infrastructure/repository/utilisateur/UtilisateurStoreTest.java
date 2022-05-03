package com.zenika.lyon.ezvelov.infrastructure.repository.utilisateur;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
