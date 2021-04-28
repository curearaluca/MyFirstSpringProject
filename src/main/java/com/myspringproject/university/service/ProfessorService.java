package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.mapper.ProfessorDtoToProfessorEntityMapper;
import com.myspringproject.university.mapper.ProfessorEntityToProfessorDtoMapper;
import com.myspringproject.university.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorEntityToProfessorDtoMapper professorEntityToProfessorDtoMapper;
    private final ProfessorDtoToProfessorEntityMapper professorDtoToProfessorEntityMapper;

    public List<ProfessorDto> getAllProfessors(){

        return professorRepository.findAll().stream()
                .map(professorEntity-> professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorEntity))
                .collect(Collectors.toList());
    }

    public ProfessorDto getProfessorById(Integer id){
        return professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorRepository.findById(id).get());
    }

    public ProfessorDto getProfessorByCnp(BigInteger cnp) {
        return professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorRepository.findByCnp(cnp));
    }

    public ProfessorDto createProfessor(ProfessorDto professorDto) {
        ProfessorEntity professorEntity = professorDtoToProfessorEntityMapper.mapProfessorDtoToEntity(professorDto);
        ProfessorEntity savedProfessorEntity= professorRepository.save(professorEntity);
        return professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(savedProfessorEntity);
    }
}
