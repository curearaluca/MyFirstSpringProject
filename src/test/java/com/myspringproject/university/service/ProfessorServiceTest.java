package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.ProfessorDtoUpdateRequest;
import com.myspringproject.university.exception.ProfessorNotFoundException;
import com.myspringproject.university.mapper.ProfessorDtoCreateRequestToProfessorEntityMapper;
import com.myspringproject.university.mapper.ProfessorEntityToProfessorDtoMapper;
import com.myspringproject.university.repository.ProfessorRepository;
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
public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private ProfessorEntityToProfessorDtoMapper professorEntityToProfessorDtoMapper;

    @Mock
    private ProfessorDtoCreateRequestToProfessorEntityMapper professorDtoCreateRequestToProfessorEntityMapper;

    @Test
    public void getAllProfessors_givenNoProfessor_thenReturnEmptyList(){
        Mockito.when(professorRepository.findAll()).thenReturn(List.of());
        List<ProfessorDto> allProfessors = professorService.getAllProfessors();
        Assertions.assertThat(allProfessors).isNotNull();
        Assertions.assertThat(allProfessors).isEmpty();
    }

    @Test
    public void getAllProfessors_givenExistingProfessors_thenReturnProfessorsList(){
        List<ProfessorEntity> professorList= new ArrayList<>();
        ProfessorEntity professorEntity= ProfessorEntity.builder()
                .cnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        professorList.add(professorEntity);
        Mockito.when(professorRepository.findAll()).thenReturn(professorList);
        ProfessorDto professorDto= ProfessorDto.builder()
                .cnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        Mockito.when(professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorEntity)).thenReturn(professorDto);
        List<ProfessorDto> allProfessors = professorService.getAllProfessors();
        Assertions.assertThat(allProfessors).isNotNull();
        Assertions.assertThat(allProfessors.size()).isEqualTo(1);
        Assertions.assertThat(allProfessors.get(0)).isNotNull();
        Assertions.assertThat(allProfessors.get(0).getFirstName()).isEqualTo("Test");
    }

    @Test
    public void given_existing_professor_when_updating_then_professor_is_updated_and_returned(){
        int id=23;
        var professor = ProfessorDtoUpdateRequest.builder()
                .id(id)
                .firstName("Test")
                .lastName("Test")
                .mail("mail_test@gmail.com")
                .salary(3000)
                .build();
        var professorEntityMock = Mockito.mock(ProfessorEntity.class);
        var professorDto=ProfessorDto.builder()
                .firstName("Test")
                .lastName("Test")
                .mail("mail_test@gmail.com")
                .salary(3000)
                .build();
        Mockito.when(professorRepository.findById(id)).thenReturn(Optional.of(professorEntityMock));
        Mockito.when(professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(ArgumentMatchers.any())).thenReturn(professorDto);
        var result = professorService.updateProfessor(professor);
        Mockito.verify(professorEntityMock).setFirstName(professor.getFirstName());
        Mockito.verify(professorEntityMock).setLastName(professor.getLastName());
        Mockito.verify(professorEntityMock).setMail(professor.getMail());
        Mockito.verify(professorEntityMock).setSalary(professor.getSalary());
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isSameAs(professorDto);
    }

    @Test
    public void given_non_existing_professor_when_updating_then_professor_not_found_exception_is_thrown(){
        //Given
        int id = 111;
        String lastName = "last name";
        var input = ProfessorDtoUpdateRequest.builder()
                .id(id)
                .lastName(lastName)
                .firstName("first name")
                .salary(8000)
                .build();
        when(professorRepository.findById(id)).thenReturn(Optional.empty());
        // When
        var throwable = catchThrowable(() -> professorService.updateProfessor(input));
        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(ProfessorNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("No professor found for id "+id);
    }

}
