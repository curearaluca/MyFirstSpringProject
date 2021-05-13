package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.StudentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StudentDtoToStudentEntityMapperTest {

    @Mock
    private StudentDtoToStudentEntityMapper studentDtoToStudentEntityMapper;

    @Test
    public void given_student_dto_when_mapping_student_entity_is_returned(){
        StudentDto studentDto=StudentDto.builder().firstName("a").build();
        StudentEntity studentEntity=StudentEntity.builder().firstName("a").build();
        var methodCall = studentDtoToStudentEntityMapper.mapStudentDtoToEntity(studentDto);
        Mockito.when(methodCall).thenReturn(studentEntity);
        assertThat(studentEntity).isNotNull();
        assertThat(studentEntity.getFirstName()).isEqualTo("a");
    }
}
