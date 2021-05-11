package com.myspringproject.university.service;

import com.myspringproject.university.repository.DeanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
@RequiredArgsConstructor
public class DeanService {

    private final DeanRepository deanRepository;

}
