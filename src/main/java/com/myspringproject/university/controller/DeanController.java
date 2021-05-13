package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.*;
import com.myspringproject.university.service.CollegeService;
import com.myspringproject.university.service.DeanService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Data
@RequestMapping("/dean")
public class DeanController {

    private final DeanService deanService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<DeanDto> getAllDeans(){
        return deanService.getAllDeans();
    }

    @GetMapping(value="/cnp/{cnp}", produces= MediaType.APPLICATION_JSON_VALUE)
    public DeanDto getDeanByCnp(@PathVariable(name= "cnp") Long cnp){
        return deanService.getDeanByCnp(cnp);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeanDto updateDean(@PathVariable(name="id") Integer id, @RequestBody @Valid DeanDtoUpdateRequest deanDto){
        deanDto.setId(id);
        return deanService.updateDean(deanDto);
    }

    @PostMapping(value = "/new/{collegeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeanDto createDeanForGivenCollegeId(@PathVariable(name= "collegeId") Integer collegeId, @RequestBody DeanDtoCreateRequest deanDto){
        return deanService.createDeanForGivenCollegeId(deanDto, collegeId);
    }

    @DeleteMapping(value="/{deanId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeanById(@PathVariable(name="deanId") Integer deanId){
        deanService.deleteDeanById(deanId);
    }

}
