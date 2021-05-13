package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.DeanEntity;
import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.domain.model.DeanDtoCreateRequest;
import com.myspringproject.university.domain.model.DeanDtoUpdateRequest;
import com.myspringproject.university.exception.DeanNotFoundException;
import com.myspringproject.university.mapper.DeanDtoCreateRequestToDeanEntityMapper;
import com.myspringproject.university.mapper.DeanEntityToDeanDtoMapper;
import com.myspringproject.university.repository.DeanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeanService {

    private final DeanRepository deanRepository;
    private final DeanEntityToDeanDtoMapper deanEntityToDeanDtoMapper;
    private final DeanDtoCreateRequestToDeanEntityMapper deanDtoCreateRequestToDeanEntityMapper;

    public DeanDto updateDean(DeanDtoUpdateRequest deanDto) {
        DeanEntity deanEntity = deanRepository.findById(deanDto.getId())
                .orElseThrow(()-> new DeanNotFoundException("No professor found for id "+ deanDto.getId()));
        deanEntity.setLastName(deanDto.getLastName());
        deanEntity.setFirstName(deanDto.getFirstName());
        deanEntity.setCnp(deanDto.getCnp());
        deanEntity.setDateOfInstalment(deanDto.getDateOfInstalment());
        if(deanDto.getTitle() != null){
            deanEntity.setTitle(deanDto.getTitle());
        }
        DeanEntity updatedDean = deanRepository.save(deanEntity);
        return deanEntityToDeanDtoMapper.mapDeanEntityToDto(updatedDean);
    }

    @Transactional(readOnly = true)
    public List<DeanDto> getAllDeans() {
            return deanRepository.findAll().stream()
                    .map(deanEntity-> deanEntityToDeanDtoMapper.mapDeanEntityToDto(deanEntity))
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DeanDto getDeanByCnp(Long cnp) {
        if(deanRepository.findByCnp(cnp) !=null) {
            return deanEntityToDeanDtoMapper.mapDeanEntityToDto(deanRepository.findByCnp(cnp));
        } else throw new DeanNotFoundException("No dean found for the given cnp");
    }

    @Transactional
    public DeanDto createDeanForGivenCollegeId(DeanDtoCreateRequest deanDto, Integer collegeId) {
        DeanEntity deanEntity = deanDtoCreateRequestToDeanEntityMapper.mapDeanDtoPostRequestToEntity(deanDto, collegeId);
        DeanEntity savedDeanEntity = deanRepository.save(deanEntity);
        return deanEntityToDeanDtoMapper.mapDeanEntityToDto(savedDeanEntity);
    }

    public void deleteDeanById(Integer deanId) {
        deanRepository.deleteById(deanId);
    }
}
