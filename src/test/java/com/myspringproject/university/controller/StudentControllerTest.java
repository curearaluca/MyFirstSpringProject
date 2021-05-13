package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    public void void_test(){
        StudentDto studentDto=StudentDto.builder().build();
        Mockito.when(studentService.getStudentById(1)).thenReturn(studentDto);
        StudentDto studentById=studentController.getStudentById(1);
        Assertions.assertThat(studentById).isSameAs(studentDto);
    }
}
