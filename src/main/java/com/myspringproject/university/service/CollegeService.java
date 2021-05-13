package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.CollegeEntity;
import com.myspringproject.university.domain.model.CollegeDto;
import com.myspringproject.university.domain.model.CollegeDtoCreateRequest;
import com.myspringproject.university.exception.CollegeNotFoundException;
import com.myspringproject.university.mapper.CollegeDtoCreateRequestToCollegeEntityMapper;
import com.myspringproject.university.mapper.CollegeEntityToCollegeDtoMapper;
import com.myspringproject.university.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollegeService {
    
    private final CollegeRepository collegeRepository;
    private final CollegeEntityToCollegeDtoMapper collegeEntityToCollegeDtoMapper;
    private final CollegeDtoCreateRequestToCollegeEntityMapper collegeDtoCreateRequestToCollegeEntityMapper;

    @Transactional(readOnly = true)
    public List<CollegeDto> getAllColleges() {
        return collegeRepository.findAll().stream()
                .map(collegeEntity -> collegeEntityToCollegeDtoMapper.mapCollegeEntityToDto(collegeEntity))
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public CollegeDto getCollegeById(Integer collegeId) {
        return collegeEntityToCollegeDtoMapper.mapCollegeEntityToDto(collegeRepository.findById(collegeId)
                .orElseThrow(() -> new CollegeNotFoundException("No college found for given id")));
    }

    @Transactional
    public CollegeDto createCollege(CollegeDtoCreateRequest collegeDto) {
        CollegeEntity collegeEntity = collegeDtoCreateRequestToCollegeEntityMapper.mapCollegeDtoPostRequestToEntity(collegeDto);
        CollegeEntity savedCollegeEntity = collegeRepository.save(collegeEntity);
        return collegeEntityToCollegeDtoMapper.mapCollegeEntityToDto(savedCollegeEntity);
    }
}
