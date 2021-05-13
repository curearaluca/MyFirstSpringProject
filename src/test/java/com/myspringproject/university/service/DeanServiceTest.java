package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.DeanEntity;
import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.mapper.DeanEntityToDeanDtoMapper;
import com.myspringproject.university.repository.DeanRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DeanServiceTest {

    @InjectMocks
    private DeanService deanService;

    @Mock
    private DeanRepository deanRepository;

    @Mock
    private DeanEntityToDeanDtoMapper deanEntityToDeanDtoMapper;

    @Test
    public void getAllDeans_givenExistingDeans_thenReturnDeansList(){
        List<DeanEntity> deanEntityList = new ArrayList<>();
        DeanEntity deanEntity = DeanEntity.builder()
                .cnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        deanEntityList.add(deanEntity);
        Mockito.when(deanRepository.findAll()).thenReturn(deanEntityList);
        DeanDto deanDto= DeanDto.builder()
                .deanCnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        Mockito.when(deanEntityToDeanDtoMapper.mapDeanEntityToDto(deanEntity)).thenReturn(deanDto);
        List<DeanDto> allDeans = deanService.getAllDeans();
        Assertions.assertThat(allDeans).isNotNull();
        Assertions.assertThat(allDeans.size()).isEqualTo(1);
        Assertions.assertThat(allDeans.get(0)).isNotNull();
        Assertions.assertThat(allDeans.get(0).getFirstName()).isEqualTo("Test");
    }

}
