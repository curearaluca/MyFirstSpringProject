package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.CourseEntity;
import com.myspringproject.university.domain.model.CourseDto;
import com.myspringproject.university.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseEntityToCourseDtoMapper {

    private final ProfessorEntityToProfessorDtoMapper professorEntityToProfessorDtoMapper;
    private final CollegeEntityToCollegeDtoMapper collegeEntityToCollegeDtoMapper;

    public CourseDto mapCourseEntityToDto(CourseEntity courseEntity){
        return CourseDto.builder()
                .id(courseEntity.getId())
                .name(courseEntity.getName())
                .credits(courseEntity.getCredits())
                .professorDto(professorEntityToProfessorDtoMapper.mapProfessorEntityToDto(courseEntity.getProfessor()))
                .collegeDto(collegeEntityToCollegeDtoMapper.mapCollegeEntityToDto(courseEntity.getCollege()))
                .build();
    }
}
