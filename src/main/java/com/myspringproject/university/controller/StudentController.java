package com.myspringproject.university.controller;

import com.myspringproject.university.domain.entity.CourseEntity;
import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.domain.model.StudentDtoCreateRequest;
import com.myspringproject.university.domain.model.StudentDtoUpdateRequest;
import com.myspringproject.university.service.StudentService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@Data
@RequestMapping("/student")
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
    public StudentDto getStudentByCnp(@PathVariable(name= "cnp") Long cnp){
        return studentService.getStudentByCnp(cnp);
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto createStudent(@RequestBody StudentDtoCreateRequest studentDto){
        return studentService.createStudent(studentDto);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto updateStudent(@PathVariable(name="id") Integer id, @RequestBody @Valid StudentDtoUpdateRequest studentDto){
        studentDto.setId(id);
        return studentService.updateStudent(studentDto);
    }

    @PostMapping(value = "/bulk")
    public List<StudentDto> createStudents(@RequestBody @Valid List<StudentDtoCreateRequest> studentDtos){
        return studentService.createStudents(studentDtos);
    }

    @PostMapping(value="/csv")
    public List<StudentDto> createStudents(@RequestParam(name="csv-file") MultipartFile file){
        return studentService.createStudentsFromFile(file);
    }

    @DeleteMapping(value="/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable(name="studentId") Integer studentId){
        studentService.deleteStudentById(studentId);
    }

    @GetMapping(value = "/{studentId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseEntity> getAllCoursesOfAStudent(@PathVariable(name = "studentId") Integer studentId){
        return studentService.getAllCoursesOfAStudent(studentId);
    }

}
