package com.myspringproject.university.exception;

public class ProfessorNotFoundException extends RuntimeException {

    public ProfessorNotFoundException(String msg){
        super(msg);
    }
}

