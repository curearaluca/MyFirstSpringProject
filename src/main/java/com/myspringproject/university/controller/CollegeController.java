package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.CollegeDto;
import com.myspringproject.university.domain.model.CollegeDtoCreateRequest;
import com.myspringproject.university.service.CollegeService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/college")
public class CollegeController {

    private final CollegeService collegeService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<CollegeDto> getAllColleges(){
        return collegeService.getAllColleges();
    }

    @GetMapping(value="/id/{collegeId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public CollegeDto getCollegeById(@PathVariable(name="collegeId") Integer collegeId){
        return collegeService.getCollegeById(collegeId);
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CollegeDto createCollege(@RequestBody CollegeDtoCreateRequest collegeDto){
        return collegeService.createCollege(collegeDto);
    }

}
