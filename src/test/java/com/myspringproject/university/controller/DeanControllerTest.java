package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.service.DeanService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeanControllerTest {

    @InjectMocks
    private DeanController deanController;

    @Mock
    private DeanService deanService;

    @Test
    public void void_test(){
        DeanDto deanDto =DeanDto.builder().build();
        Mockito.when(deanService.getDeanByCnp(Long.valueOf("1000000000000"))).thenReturn(deanDto);
        DeanDto deanByCnp=deanController.getDeanByCnp(Long.valueOf("1000000000000"));
        Assertions.assertThat(deanByCnp).isSameAs(deanDto);
    }
}
