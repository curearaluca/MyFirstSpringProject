package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.service.ProfessorService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@Data
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ProfessorDto> getAllStudents(){
        return professorService.getAllProfessors();
    }

    @GetMapping(value="/id/{professorId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto getProfessorById(@PathVariable(name="professorId") Integer professorId){
        return professorService.getProfessorById(professorId);
    }

    @GetMapping(value="/cnp/{cnp}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto getProfessorByCnp(@PathVariable(name= "cnp") BigInteger cnp){
        return professorService.getProfessorByCnp(cnp);
    }

    @PostMapping("/new")
    public ProfessorDto createProfessor(ProfessorDto professorDto){
        return professorService.createProfessor(professorDto);
    }
}
