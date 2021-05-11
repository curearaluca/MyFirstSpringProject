package com.myspringproject.university.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String msg){
        super(msg);
    }
}

