package com.myspringproject.university.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollegeDto {

    private Integer collegeId;
    private String name;
    private DeanDto dean;

}
