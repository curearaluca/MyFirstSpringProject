package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorDtoToProfessorEntityMapperTest {

    @Mock
    private ProfessorDtoToProfessorEntityMapper professorDtoToProfessorEntityMapper;

    @Test
    public void given_student_dto_when_mapping_student_entity_is_returned(){
        ProfessorDto professorDto =ProfessorDto.builder().salary(9000).build();
        ProfessorEntity professorEntity =ProfessorEntity.builder().salary(9000).build();
        var methodCall = professorDtoToProfessorEntityMapper.mapProfessorDtoToEntity(professorDto);
        Mockito.when(methodCall).thenReturn(professorEntity);
        assertThat(professorEntity).isNotNull();
        assertThat(professorEntity.getSalary()).isEqualTo(9000);
    }
}
