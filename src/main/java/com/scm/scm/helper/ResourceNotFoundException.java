package com.scm.scm.helper;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }

    ResourceNotFoundException(){
        super("Reousce not found");
    }
    
}
