package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.DeanEntity;
import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.domain.model.DeanDtoUpdateRequest;
import com.myspringproject.university.exception.DeanNotFoundException;
import com.myspringproject.university.mapper.DeanEntityToDeanDtoMapper;
import com.myspringproject.university.repository.DeanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeanService {

    private final DeanRepository deanRepository;
    private final DeanEntityToDeanDtoMapper deanEntityToDeanDtoMapper;

    public DeanDto updateDean(DeanDtoUpdateRequest deanDto) {
        DeanEntity deanEntity = deanRepository.findById(deanDto.getId())
                .orElseThrow(()-> new DeanNotFoundException("No professor found for id "+ deanDto.getId()));
        deanEntity.setLastName(deanDto.getLastName());
        deanEntity.setFirstName(deanDto.getFirstName());
        deanEntity.setCnp(deanDto.getCnp());
        if(deanDto.getTitle() != null){
            deanEntity.setTitle(deanDto.getTitle());
        }
        if(deanDto.getDateOfInstalment() != null){
            deanEntity.setDateOfInstalment(deanDto.getDateOfInstalment());
        }
        DeanEntity updatedDean = deanRepository.save(deanEntity);
        return deanEntityToDeanDtoMapper.mapDeanEntityToDto(updatedDean);
    }
}
