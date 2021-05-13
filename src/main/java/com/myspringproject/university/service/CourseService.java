package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.CourseEntity;
import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.CourseDto;
import com.myspringproject.university.exception.CourseNotFoundException;
import com.myspringproject.university.exception.ProfessorNotFoundException;
import com.myspringproject.university.exception.StudentNotFoundException;
import com.myspringproject.university.mapper.CourseEntityToCourseDtoMapper;
import com.myspringproject.university.repository.CourseRepository;
import com.myspringproject.university.repository.ProfessorRepository;
import com.myspringproject.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseEntityToCourseDtoMapper courseEntityToCourseDtoMapper;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses(){
        return courseRepository.findAll().stream()
                .map(courseEntity-> courseEntityToCourseDtoMapper.mapCourseEntityToDto(courseEntity))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCoursesByCollegeId(Integer collegeId) {
        return courseRepository.findAllByCollege_CollegeId(collegeId)
                .stream()
                .map(courseEntity -> courseEntityToCourseDtoMapper.mapCourseEntityToDto(courseEntity))
                .collect(toList());
    }

    public List<CourseDto> getAllCoursesByProfessorId(Integer professorId) {
        return courseRepository.findAllByProfessor_Id(professorId)
                .stream()
                .map(courseEntity -> courseEntityToCourseDtoMapper.mapCourseEntityToDto(courseEntity))
                .collect(toList());
    }

    @Transactional
    public CourseEntity registerStudentToCourse(Integer courseId, Integer studentId){
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course id does not exist"));
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student id does not exist"));
        courseEntity.registerStudent(studentEntity);
        return courseRepository.save(courseEntity);
    }

    @Transactional
    public CourseEntity assignProfessorToCourse(Integer courseId, Integer professorId){
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course id does not exist"));
        ProfessorEntity professorEntity = professorRepository.findById(professorId).orElseThrow(()->new ProfessorNotFoundException("Professor id does not exist"));
        courseEntity.assignProfessor(professorEntity);
        return courseRepository.save(courseEntity);
    }
}
