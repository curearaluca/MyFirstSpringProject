package com.myspringproject.university.controller;

import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.service.StudentService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@Data
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping(value="/id/{studentId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getStudentById(@PathVariable(name="studentId") Integer studentId){
        return studentService.getStudentById(studentId);
    }

    @GetMapping(value="/cnp/{cnp}", produces= MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getStudentByCnp(@PathVariable(name= "cnp") BigInteger cnp){
        return studentService.getStudentByCnp(cnp);
    }

    @PostMapping("/new")
    public StudentDto createStudent(StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }

}
