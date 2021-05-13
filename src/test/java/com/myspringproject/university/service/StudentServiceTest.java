package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.ProfessorDtoUpdateRequest;
import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.domain.model.StudentDtoUpdateRequest;
import com.myspringproject.university.exception.ProfessorNotFoundException;
import com.myspringproject.university.exception.StudentNotFoundException;
import com.myspringproject.university.mapper.StudentDtoCreateRequestToStudentEntityMapper;
import com.myspringproject.university.mapper.StudentEntityToStudentDtoMapper;
import com.myspringproject.university.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentEntityToStudentDtoMapper studentEntityToStudentDtoMapper;

    @Mock
    private StudentDtoCreateRequestToStudentEntityMapper studentDtoCreateRequestToStudentEntityMapper;

    @Test
    public void getAllStudents_givenNoStudent_thenReturnEmptyList(){
        Mockito.when(studentRepository.findAll()).thenReturn(List.of());
        List<StudentDto> allStudents = studentService.getAllStudents();
        Assertions.assertThat(allStudents).isNotNull();
        Assertions.assertThat(allStudents).isEmpty();
    }

    @Test
    public void getAllStudents_givenExistingStudents_thenReturnStudentsList(){
        List<StudentEntity> studentList = new ArrayList<>();
        StudentEntity studentEntity= StudentEntity.builder()
                .cnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        studentList.add(studentEntity);
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);
        StudentDto studentDto= StudentDto.builder()
                .cnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        Mockito.when(studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentEntity)).thenReturn(studentDto);
        List<StudentDto> allStudents = studentService.getAllStudents();
        Assertions.assertThat(allStudents).isNotNull();
        Assertions.assertThat(allStudents.size()).isEqualTo(1);
        Assertions.assertThat(allStudents.get(0)).isNotNull();
        Assertions.assertThat(allStudents.get(0).getFirstName()).isEqualTo("Test");
    }

    @Test
    public void given_existing_student_when_updating_then_student_is_updated_and_returned() {
        int id = 23;
        var student = StudentDtoUpdateRequest.builder()
                .id(id)
                .firstName("Test")
                .lastName("Test")
                .mail("mail_test@gmail.com")
                .build();
        var studentEntityMock = Mockito.mock(StudentEntity.class);
        var studentDto = StudentDto.builder()
                .firstName("Test")
                .lastName("Test")
                .mail("mail_test@gmail.com")
                .build();
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(studentEntityMock));
        Mockito.when(studentEntityToStudentDtoMapper.mapStudentEntityToDto(ArgumentMatchers.any())).thenReturn(studentDto);
        var result = studentService.updateStudent(student);
        Mockito.verify(studentEntityMock).setFirstName(student.getFirstName());
        Mockito.verify(studentEntityMock).setLastName(student.getLastName());
        Mockito.verify(studentEntityMock).setMail(student.getMail());
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isSameAs(studentDto);
    }

    @Test
    public void given_non_existing_student_when_updating_then_student_not_found_exception_is_thrown(){
        //Given
        int id = 222;
        String lastName = "last name";
        var input = StudentDtoUpdateRequest.builder()
                .id(id)
                .lastName(lastName)
                .firstName("first name")
                .build();
        when(studentRepository.findById(id)).thenReturn(Optional.empty());
        // When
        var throwable = catchThrowable(() -> studentService.updateStudent(input));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(StudentNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Student not found for id "+id);
    }
}
