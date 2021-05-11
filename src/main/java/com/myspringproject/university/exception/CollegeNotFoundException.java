package com.myspringproject.university.exception;

public class CollegeNotFoundException extends RuntimeException {

    public CollegeNotFoundException(String msg){
        super(msg);
    }
}

