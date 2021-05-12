package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.domain.model.DeanDtoUpdateRequest;
import com.myspringproject.university.service.DeanService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Data
@RequestMapping("/dean")
public class DeanController {

    private final DeanService deanService;

    //see if this updates the deanDto in the college with the corresponding id as well

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeanDto updateDean(@PathVariable(name="id") Integer id, @RequestBody @Valid DeanDtoUpdateRequest deanDto){
        deanDto.setId(id);
        return deanService.updateDean(deanDto);
    }

}
