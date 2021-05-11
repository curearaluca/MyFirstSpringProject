package com.myspringproject.university.exception;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(String msg){
        super(msg);
    }
}
