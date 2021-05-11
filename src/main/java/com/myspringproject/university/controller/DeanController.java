package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.service.DeanService;
import lombok.Data;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("/dean")
public class DeanController {

    private final DeanService deanService;

    //update dean (delete la fostul dean + create new dean, din aceeasi metoda)
    @PutMapping("/update")
    public DeanDto updateDean(){
        //work in progress
        return null;
    }
}
