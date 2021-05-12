package com.myspringproject.university.exception;

public class DeanNotFoundException extends RuntimeException{
    public DeanNotFoundException(String msg){
        super(msg);
    }
}
