package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.service.ProfessorService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorControllerTest {

    @InjectMocks
    private ProfessorController professorController;

    @Mock
    private ProfessorService professorService;

    @Test
    public void void_test(){
        ProfessorDto professorDto =ProfessorDto.builder().build();
        Mockito.when(professorService.getProfessorById(1)).thenReturn(professorDto);
        ProfessorDto professorById=professorController.getProfessorById(1);
        Assertions.assertThat(professorById).isSameAs(professorDto);
    }
}
