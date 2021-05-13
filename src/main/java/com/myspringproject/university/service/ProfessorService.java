package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.ProfessorDtoCreateRequest;
import com.myspringproject.university.domain.model.ProfessorDtoUpdateRequest;
import com.myspringproject.university.exception.FileNotFoundException;
import com.myspringproject.university.exception.ProfessorNotFoundException;
import com.myspringproject.university.mapper.ProfessorDtoCreateRequestToProfessorEntityMapper;
import com.myspringproject.university.mapper.ProfessorEntityToProfessorDtoMapper;
import com.myspringproject.university.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorEntityToProfessorDtoMapper professorEntityToProfessorDtoMapper;
    private final ProfessorDtoCreateRequestToProfessorEntityMapper professorDtoCreateRequestToProfessorEntityMapper;

    @Transactional(readOnly = true)
    public List<ProfessorDto> getAllProfessors(){
        return professorRepository.findAll().stream()
                .map(professorEntity-> professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorEntity))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfessorDto getProfessorById(Integer id){
        return professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public ProfessorDto getProfessorByCnp(Long cnp) {
        return professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorRepository.findByCnp(cnp));
    }

    @Transactional
    public ProfessorDto createProfessor(ProfessorDtoCreateRequest professorDto) {
        ProfessorEntity professorEntity = professorDtoCreateRequestToProfessorEntityMapper.mapProfessorDtoPostRequestToEntity(professorDto);
        ProfessorEntity savedProfessorEntity= professorRepository.save(professorEntity);
        return professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(savedProfessorEntity);
    }

    @Transactional
    public ProfessorDto updateProfessor(ProfessorDtoUpdateRequest professorDto) {
        ProfessorEntity professorEntity = professorRepository.findById(professorDto.getId())
                .orElseThrow(()-> new ProfessorNotFoundException("No professor found for id "+ professorDto.getId()));
        professorEntity.setLastName(professorDto.getLastName());
        professorEntity.setFirstName(professorDto.getFirstName());
        if(professorDto.getSalary() != null){
            professorEntity.setSalary(professorDto.getSalary());
        }
        if(professorDto.getMail() != null){
            professorEntity.setMail(professorDto.getMail());
        }
        ProfessorEntity updatedProfessor = professorRepository.save(professorEntity);
        return professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(updatedProfessor);
    }

    @Transactional
    public List<ProfessorDto> createProfessors(List<ProfessorDtoCreateRequest> professorsList) {
        return professorsList.stream()
                .map(professor-> professorDtoCreateRequestToProfessorEntityMapper.mapProfessorDtoPostRequestToEntity(professor))
                .map(professorEntity -> professorRepository.save(professorEntity))
                .map(professorSaved -> professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(professorSaved))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Transactional
    public List<ProfessorDto> createProfessorsFromFile(MultipartFile file) {
        if(file.isEmpty()){
            throw new FileNotFoundException("File provided is empty");
        }
        byte[] bytes = file.getBytes();
        String fileContent = new String(bytes);
        String[] rows = fileContent.split("\r\n");
        List<ProfessorDtoCreateRequest> list= new ArrayList<>();
        for(String row: rows){
            String[] rowSplit=row.split(",");
            if(rowSplit.length != 0) {
                ProfessorDtoCreateRequest professor=ProfessorDtoCreateRequest.builder()
                        .cnp(Long.valueOf(rowSplit[0]))
                        .firstName(rowSplit[1])
                        .lastName(rowSplit[2])
                        .collegeId(Integer.valueOf(rowSplit[3]))
                        .build();
                list.add(professor);
            }
        }
        return createProfessors(list);
    }

    public void deleteProfessorById(Integer professorId) {
        professorRepository.deleteById(professorId);
    }
}
