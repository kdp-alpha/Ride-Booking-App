package com.karan.project.ride.ridebookingApp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
