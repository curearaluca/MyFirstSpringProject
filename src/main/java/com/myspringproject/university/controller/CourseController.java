package com.myspringproject.university.controller;

import com.myspringproject.university.domain.entity.CourseEntity;
import com.myspringproject.university.domain.model.CourseDto;
import com.myspringproject.university.repository.CourseRepository;
import com.myspringproject.university.service.CourseService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping(value = "/byCollege/{collegeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getAllCoursesByCollege(@PathVariable(name = "collegeId") Integer collegeId){
        return courseService.getAllCoursesByCollegeId(collegeId);
    }

    @GetMapping(value = "/byProfessor/{professorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getAllCoursesByProfessor(@PathVariable(name = "professorId") Integer professorId){
        return courseService.getAllCoursesByProfessorId(professorId);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public CourseEntity registerStudentToCourse(@PathVariable(name = "courseId") Integer courseId, @PathVariable(name = "studentId") Integer studentID){
        return courseService.registerStudentToCourse(courseId, studentID);
    }

    @PutMapping("/{courseId}/professor/{professorId}")
    public CourseEntity assignProfessorToCourse(@PathVariable(name = "courseId") Integer courseId, @PathVariable(name = "professorId") Integer professorId){
        return courseService.assignProfessorToCourse(courseId, professorId);
    }

}
