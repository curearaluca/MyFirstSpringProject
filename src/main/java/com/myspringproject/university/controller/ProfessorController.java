package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.ProfessorDtoCreateRequest;
import com.myspringproject.university.domain.model.ProfessorDtoUpdateRequest;
import com.myspringproject.university.service.ProfessorService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@Data
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ProfessorDto> getAllProfessors(){
        return professorService.getAllProfessors();
    }

    @GetMapping(value="/id/{professorId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto getProfessorById(@PathVariable(name="professorId") Integer professorId){
        return professorService.getProfessorById(professorId);
    }

    @GetMapping(value="/cnp/{cnp}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto getProfessorByCnp(@PathVariable(name= "cnp") Long cnp){
        return professorService.getProfessorByCnp(cnp);
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto createProfessor(@RequestBody ProfessorDtoCreateRequest professorDto){
        return professorService.createProfessor(professorDto);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto updateProfessor(@PathVariable(name="id") Integer id, @RequestBody @Valid ProfessorDtoUpdateRequest professorDto){
        professorDto.setId(id);
        return professorService.updateProfessor(professorDto);
    }

    @PostMapping(value = "/bulk")
    public List<ProfessorDto> createProfessors(@RequestBody @Valid List<ProfessorDtoCreateRequest> professorDtos){
        return professorService.createProfessors(professorDtos);
    }

    @PostMapping(value = "/csv")
    public List<ProfessorDto> createProfessors(@RequestParam(name="csv-file") MultipartFile file){
        return professorService.createProfessorsFromFile(file);
    }

    @DeleteMapping(value="/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessorById(@PathVariable(name="professorId") Integer professorId){
        professorService.deleteProfessorById(professorId);
    }

}
